package DAO;

import Entidades.Usuario;
import Interfaces.IUsuarioDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Carlo
 */
public class UsuarioDAO implements IUsuarioDAO {

   private final MongoDatabase database;
    private static UsuarioDAO instance;

    // Constructor público que recibe la conexión como parámetro
    public UsuarioDAO(MongoDatabase database) {
        this.database = database;
    }

    // Método para obtener una instancia única de UsuarioDAO
    public static UsuarioDAO getInstance(MongoDatabase database) {
        if (instance == null) {
            instance = new UsuarioDAO(database);
        }
        return instance;
    }

    @Override
    public Usuario log(String usuario, String pass) throws PersistenciaException {
        MongoCollection<Usuario> collection = database.getCollection("usuarios", Usuario.class);

    Usuario result = collection.find(eq("usuario", usuario)).first();
    if (result != null && result.getPass().equals(pass)) {
        return result;
    }
    return null;
    }

    @Override
    public void Registrar(Usuario reg) throws PersistenciaException {
        MongoCollection<Usuario> collection = database.getCollection("usuarios", Usuario.class);
        collection.insertOne(reg);
    }

    @Override
    public List<Usuario> ListarUsuarios() throws PersistenciaException {
        MongoCollection<Usuario> collection = database.getCollection("usuarios", Usuario.class);
        List<Usuario> usuarios = new ArrayList<>();
        collection.find().iterator().forEachRemaining(usuarios::add);
        return usuarios;
    }
}
