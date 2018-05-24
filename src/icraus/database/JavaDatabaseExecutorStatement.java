/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.Statement;

/**
 *
 * @author Shoka
 */
public class JavaDatabaseExecutorStatement extends Statement{
    public static String DATABASE_STATMENT_VAR = "$$DATABASE_STATMENT";
    public static String JAVA_EXECUTOR_TEMPLATE = "execute(" + DATABASE_STATMENT_VAR + ")";
    private Statement databaseStatment;

    public JavaDatabaseExecutorStatement(Statement databaseStatment) {
        this.databaseStatment = databaseStatment;
    }
    
    @Override
    public String toText() throws ErrorGenerateCodeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Statement getDatabaseStatment() {
        return databaseStatment;
    }

    public void setDatabaseStatment(Statement databaseStatment) {
        this.databaseStatment = databaseStatment;
    }
    
}
