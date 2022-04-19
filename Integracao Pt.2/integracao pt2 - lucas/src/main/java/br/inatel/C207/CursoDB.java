package br.inatel.C207;

import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDB extends Database{

    public boolean insertCurso(Curso curso){
        connect();
        String sql = "INSERT INTO curso(nome) VALUES (?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, curso.getNome());
            pst.execute();
            check = true;
        }
        catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public ArrayList<Curso> researchCurso(){
        connect();
        ArrayList<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * from curso";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                Curso cursoTemp = new Curso(result.getString("nome"));
                cursoTemp.id = result.getInt("id");
                System.out.println("ID = " + cursoTemp.id);
                System.out.println("Nome = " + cursoTemp.getNome());
                System.out.println("-------------------------------");
                cursos.add(cursoTemp);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return cursos;
    }

    public boolean updateCurso(int id, String nome){
        connect();
        String sql = "UPDATE curso SET nome=? WHERE id=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean deleteCurso(int id){
        connect();
        String sql = "DELETE FROM curso WHERE id=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }
}
