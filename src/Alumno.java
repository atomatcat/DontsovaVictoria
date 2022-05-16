
    import java.sql.Connection;
    import java.sql.Date;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    /*
     * Diseñar la clase alumno que tiene los siguientes atributos, 
     * de manera que se pueda almacenar sus objetos en la base de datos
     * instituto.
     * 
     * - id: identificativo único que distingue de forma unívoca
     * - nombre: no tendrá más de 30 caracteres
     * - fnac: fecha de nacimiento 
     * - notaMedia: la nota media del alumno
     * - curso: curso al que pertenece el alumno
     * 
     * */
    
    public class Alumno {
        private final int TAM_MAX_NOMBRE= 30;
        private final int TAM_MAX_CURSO= 2;
        
        private int id;
        private String nombre;//longitud máxima 30
        private Date fnac;
        private double notaMedia;
        private String curso;//longitud máxima 2
        
        public Alumno(int id) {
            this.id= id;
            
        }
        
        public Alumno(int id, String nombre, Date fnac, double notaMedia, String curso) {
            this.id=id;
            this.nombre= nombre;
            this.fnac= fnac;
            this.notaMedia= notaMedia;
            this.curso= curso;
        }
        
        public int getId() {
            return id;
        }
        
        public void setId (int id) {
            this.id = id;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public void setNombre (String nombre) {//para que coja sólo los valores entre 0 y 30
            this.nombre=nombre.substring(0,Math.min(TAM_MAX_CURSO, curso.length()));
        }
        
        public Date getFNac () {
            return fnac;
        }
        
        public void setFNac (Date fnac) {
            this.fnac= fnac;
        }
    
        public double getNotaMedia () {
            return notaMedia;
        }
        
        public void setNotaMedia (Double notaMedia) {
            this.notaMedia= notaMedia;
        }
    
        public String getCurso () {
            return curso;
        }
        
        public void setCurso (String curso) {//2BBBb
            this.curso= curso.substring(0,Math.min(TAM_MAX_CURSO, curso.length()));//Para que da igual lo que metas, 
            //te cogerá los dos primeros dígitos (coge el mínimo entra la constante y el tamano que introduzcamos
            //por ejmplo si introducimos 4 letras o números cogerá el mínimo entre el TAM_MAX_CURSO y el tamaño.
            //Cogerá la constante o menos.
        }
    
        static private Connection conexion() {
            Connection con = null; 
            String url = "jdbc:mysql://localhost/instituto";
            
            try {
                con = DriverManager.getConnection(url, "pepe", "12345");
                
                
            } catch (SQLException e) {
            System.out.println(e.getMessage());	
            }	
            return con;
        }
        
        
        public void create() {//Método para crear un alumno
            //Normalmente este tipo de métodos iría fuera de la clase Alumno, pero hoy lo vamos a crear aquí
            Connection con = conexion();
            
            String sql = "INSERT INTO alumno (num, nombre, fnac, media, curso)" + 
                    "VALUES (?,?,?,?,?)";
            try {
                
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);
            sentencia.setString(2, nombre);
            sentencia.setDate(3, fnac);
            sentencia.setDouble(4, notaMedia);
            sentencia.setString(5, curso);
            
            sentencia.executeUpdate();
            con.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        
        static public Alumno read (int id) {//método que devuelve un objeto de la clase alumno
            Alumno alumno= null;
            String sql = "SELECT * FROM alumno WHERE num= ?";
            try {
                Connection con = conexion();
                PreparedStatement sentencia = con.prepareStatement(sql);
                sentencia.setInt(1, id);
                ResultSet rs = sentencia.executeQuery();
                while (rs.next()) {
                    String nombre= rs.getString("nombre");
                    Date fnac = rs.getDate("fnac");
                    double media = rs.getDouble("media");
                    String curso = rs.getString("curso");
                    alumno= new Alumno(id, nombre, fnac, media, curso);
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());		
            }
            
            return alumno;
        }
        
    }
    

