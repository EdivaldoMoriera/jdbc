package agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agenda.factory.ConnectionFactory;
import agenda.model.Contato;

public class ContatoDAO
{

    public void save(Contato contato)
    {

        /*
         * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
         * na base de dados
         */
        String sql = "INSERT INTO contatos(nome,idade,endereco,dataCadastro)"
                + " VALUES(?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try
        {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnection();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, contato.getNome());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setInt(2, contato.getIdade());

            pstm.setString(3, contato.getEndereco());

            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e)
        {

            e.printStackTrace();
        } finally
        {

            //Fecha as conexões
            try
            {
                if (pstm != null)
                {

                    pstm.close();
                }

                if (conn != null)
                {
                    conn.close();
                }

            } catch (Exception e)
            {

                e.printStackTrace();
            }
        }
    }

    public void removeById(int id)
    {

        String sql = "DELETE FROM contatos WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try
        {
            conn = ConnectionFactory.createConnection();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {

            try
            {
                if (pstm != null)
                {

                    pstm.close();
                }

                if (conn != null)
                {
                    conn.close();
                }

            } catch (Exception e)
            {

                e.printStackTrace();
            }
        }
    }

    public void update(Contato contato)
    {

        String sql = "UPDATE contatos SET nome = ?, idade = ?,endereco = ?, dataCadastro = ?"
                + " WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try
        {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnection();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, contato.getNome());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setInt(2, contato.getIdade());
            //inserido
            pstm.setString(3, contato.getEndereco());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));

            pstm.setInt(5, contato.getId());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e)
        {

            e.printStackTrace();
        } finally
        {

            //Fecha as conexões
            try
            {
                if (pstm != null)
                {

                    pstm.close();
                }

                if (conn != null)
                {
                    conn.close();
                }

            } catch (Exception e)
            {

                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos()
    {

        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try
        {
            conn = ConnectionFactory.createConnection();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next())
            {

                Contato contato = new Contato();

                //Recupera o id do banco e atribui ele ao objeto
                contato.setId(rset.getInt("id"));

                //Recupera o nome do banco e atribui ele ao objeto
                contato.setNome(rset.getString("nome"));

                //Recupera a idade do banco e atribui ele ao objeto
                contato.setIdade(rset.getInt("idade"));
                //  Inseiro
                contato.setEndereco(rset.getString("endereco"));

                //Recupera a data do banco e atribui ela ao objeto
                contato.setDataCadastro(rset.getDate("dataCadastro"));

                //Adiciono o contato recuperado, a lista de contatos
                contatos.add(contato);
            }
        } catch (Exception e)
        {

            e.printStackTrace();
        } finally
        {

            try
            {

                if (rset != null)
                {

                    rset.close();
                }

                if (pstm != null)
                {

                    pstm.close();
                }

                if (conn != null)
                {
                    conn.close();
                }

            } catch (Exception e)
            {

                e.printStackTrace();
            }
        }

        return contatos;
    }
}
