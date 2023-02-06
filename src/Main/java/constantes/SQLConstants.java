package Main.java.constantes;

public class SQLConstants {
    public static final String SELECTUSERS = "SELECT * FROM user WHERE NOM = ? AND BADGE = ? AND PASSWORD = ? AND ISADMIN = ?";
    public static final String INSERTUSER = "INSERT INTO user(NOM,BADGE,PASSWORD,ISADMIN) VALUES(?,?,?,?)";

    public static final String SELECTUSER = "SELECT * FROM user WHERE BADGE = ? AND PASSWORD = ? ";
    public static final String SELECTEMPLOYEE = "SELECT * FROM `employee`";
}
