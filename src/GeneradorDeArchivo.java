import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivo {
    public void guardarJson(Monedas monedas) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter escritura = new FileWriter(monedas.base_code() + "_to_" + monedas.target_code() + ".json")) {
            gson.toJson(monedas, escritura);
        } catch (IOException e) {
            throw new IOException("Error al escribir el archivo JSON: " + e.getMessage(), e);
        }
    }
}

