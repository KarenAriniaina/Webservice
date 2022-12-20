/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author karen
 */
public class ObjetBDD {

    private String PrimaryKey;
    private String NomTable;
    private String PointToDelete;

    public String getPointToDelete() {
        return PointToDelete;
    }

    public void setPointToDelete(String pointToDelete) {
        PointToDelete = pointToDelete;
    }

    public String getPrimaryKey() {
        return PrimaryKey;
    }

    public void setPrimaryKey(String PrimaryKey) {
        this.PrimaryKey = PrimaryKey;
    }

    public String getNomTable() {
        return NomTable;
    }

    public void setNomTable(String NomTable) {
        this.NomTable = NomTable;
    }

    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isNumeric(String str) {
        return isDouble(str) == true || isInteger(str) == true;
    }

    public boolean verifier(String value) {
        boolean normal = true;
        if (isNumeric(value) == true) {
            if (isDouble(value) == true) {
                double d = (double) Double.parseDouble(value);
                if (d == 0.0 || d < 0.0) {
                    normal = false;
                }
            } else if (isInteger(value) == true) {
                int d = (int) Integer.parseInt(value);
                if (d == 0 || d < 0) {
                    normal = false;
                }
            }
        }
        return normal;
    }

    public void Create(Connection c) throws Exception {
        boolean nullve = false;
        Statement st = null;
        try {
            if (c == null) {
                c = Connexion.getConnection();
                nullve = true;
            }
            String sql = "";
            String donnee = "";
            Class cl = this.getClass();
            sql = sql + "INSERT INTO " + NomTable + " (" + PrimaryKey;
            for (int i = 0; i < cl.getDeclaredFields().length; i++) {
                String field = cl.getDeclaredFields()[i].getName();
                if (verif_column(field, c) == true) {
                    if (field.equalsIgnoreCase(PrimaryKey) == false) {
                        for (int m = 0; m < cl.getMethods().length; m++) {
                            if (cl.getMethods()[m].getName().equalsIgnoreCase("get" + field) == true) {
                                Object ob = cl.getMethods()[m].invoke(this);
                                if (ob != null) {
                                    String value = ob.toString();
                                    boolean normal = verifier(value);
                                    if (normal == true) {
                                        sql = sql + "," + field;
                                        if ((isDouble(value) == true || isInteger(value) == true) && this.getClass().getDeclaredFields()[i].getType().isPrimitive()) {
                                            donnee = donnee + "," + value;
                                        } else {
                                            donnee = donnee + ",'" + value + "'";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sql += ")";
            donnee += ")";
            sql = sql + "VALUES ('" + NomTable + "_'|| nextval('s_" + NomTable + "')" + donnee;
            st = c.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            if (nullve && c != null) {
                c.close();
            }
        }
    }

    public void Update(Connection c) throws Exception {
        boolean nullve = false;
        Statement st = null;
        try {
            if (c == null) {
                c = Connexion.getConnection();
                nullve = true;
            }
            String sql = "";
            String pkval = "";
            sql = sql + "UPDATE " + NomTable + " SET ";
            Class cl = this.getClass();
            for (int i = 0; i < cl.getDeclaredFields().length; i++) {
                String field = cl.getDeclaredFields()[i].getName();
                if (verif_column(field, c) == true) {
                    for (int m = 0; m < cl.getMethods().length; m++) {
                        if (cl.getMethods()[m].getName().equalsIgnoreCase("get" + field) == true) {
                            Object ob = cl.getMethods()[m].invoke(this);
                            if (ob != null) {
                                String value = ob.toString();
                                boolean normal = verifier(value);
                                if (normal == true) {
                                    if (field.equalsIgnoreCase(PrimaryKey) == false) {
                                        if (isDouble(pkval) == true || isInteger(pkval) == true) {
                                            sql = sql + field + " =" + value;
                                            if (i < cl.getDeclaredFields().length - 1) {
                                                sql = sql + ", ";
                                            }
                                        } else {
                                            sql = sql + field + " = '" + value + "'";
                                            if (i < cl.getDeclaredFields().length - 1) {
                                                sql = sql + ", ";
                                            }
                                        }
                                    } else {
                                        pkval = value;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sql = sql + " WHERE " + PrimaryKey + "='" + pkval + "'";
            st = c.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            if (nullve && c != null) {
                c.close();
            }
        }
    }

    public ObjetBDD[] Find(Connection c) throws Exception {
        boolean nullve = false;
        ObjetBDD[] val = new ObjetBDD[0];
        Statement st = null;
        ResultSet sett = null;
        ResultSet set = null;
        try {
            if (c == null) {
                c = Connexion.getConnection();
                nullve = true;
            }
            ObjetBDD[] valiny = new ObjetBDD[100];
            String sql = "SELECT * FROM " + NomTable + " WHERE 1=1";
            st = c.createStatement();
            sett = st.executeQuery(sql);
            for (int i = 0; i < this.getClass().getDeclaredFields().length; i++) {
                String field = this.getClass().getDeclaredFields()[i].getName();
                if (verif_column(field, c) == true) {
                    for (int m = 0; m < this.getClass().getMethods().length; m++) {
                        if (this.getClass().getMethods()[m].getName().equalsIgnoreCase("get" + field) == true) {
                            Object ob = this.getClass().getMethods()[m].invoke(this);
                            if (ob != null) {
                                String value = ob.toString();
                                boolean normal = verifier(value);
                                if (normal == true) {
                                    if (verif_col(sett, field) == true) {
                                        if ((isDouble(value) == true || isInteger(value) == true) && this.getClass().getDeclaredFields()[i].getType().isPrimitive()) {
                                            sql = sql + " AND " + field + " = " + value;
                                        } else {
                                            sql = sql + " AND " + field + " = '" + value + "'";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            set = st.executeQuery(sql);
            int v = 0;
            int vale = 0;
            Class cl = this.getClass();
            while (set.next() == true) {
                valiny[v] = this.getClass().newInstance();
                for (int i = 0; i < cl.getDeclaredFields().length; i++) {
                    String field = cl.getDeclaredFields()[i].getName();
                    if (verif_col(set, field.toLowerCase()) == true) {
                        Method m = cl.getDeclaredMethod("set" + field.substring(0, 1).toUpperCase() + field.substring(1), cl.getDeclaredFields()[i].getType());
                        field = field.toLowerCase();
                        m.invoke(valiny[v], set.getObject(field));
                    }
                }
                //superclass
                for (int i = 0; i < cl.getSuperclass().getDeclaredFields().length; i++) {
                    String field = cl.getSuperclass().getDeclaredFields()[i].getName();
                    if (verif_col(set, field.toLowerCase()) == true) {
                        Method m = cl.getSuperclass().getMethod("set" + field.substring(0, 1).toUpperCase() + field.substring(1), cl.getSuperclass().getDeclaredFields()[i].getType());
                        field = field.toLowerCase();
                        m.invoke(valiny[v], set.getObject(field));
                    }
                }
                v++;
                vale++;
            }
            val = new ObjetBDD[vale];
            System.arraycopy(valiny, 0, val, 0, val.length);
            if (!nullve) {
                c.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (set != null) {
                set.close();
            }
            if (sett != null) {
                sett.close();
            }
            if (st != null) {
                st.close();
            }
            if (nullve && c != null) {
                c.close();
            }
        }
        return val;
    }

    //verifier si attribut classe correspond au colonne du resultset d'un select
    public boolean verif_col(ResultSet r, String col) throws Exception {
        boolean valiny = false;
        ResultSetMetaData md = r.getMetaData();
        int nbrcol = md.getColumnCount();
        for (int i = 1; i <= nbrcol; i++) {
            String column = md.getColumnName(i);
            if (column.equalsIgnoreCase(col) == true) {
                valiny = true;
            }
        }
        return valiny;
    }

    //verifier si attribut classe appartient à colonne base de donnée
    public boolean verif_column(String nCol, Connection c) throws Exception {
        String lowcaseTable = NomTable.toLowerCase();
        String lowcaseCol = nCol.toLowerCase();
        DatabaseMetaData md = c.getMetaData();
        ResultSet rs = md.getColumns(null, null, lowcaseTable, lowcaseCol);
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public void Delete() throws Exception {
        Statement st = null;
        Connection c = null;
        try {
            //if (c == null) {
            c = Connexion.getConnection();
            //  nullve = true;
            //}
            String sql = "";
            sql = sql + "DELETE FROM " + NomTable + " WHERE " + PointToDelete + "='";
            Method m = this.getClass().getDeclaredMethod("get" + PointToDelete.substring(0, 1).toUpperCase() + PointToDelete.substring(1));
            String s = m.invoke(this).toString();
            sql += s + "'";
            System.out.println(sql);
            st = c.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
}
