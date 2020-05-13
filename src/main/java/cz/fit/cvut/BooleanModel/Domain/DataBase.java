package cz.fit.cvut.BooleanModel.Domain;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class DataBase {
    private ArrayList<Document> documents;
    
    public DataBase(String folder) {
        documents = new ArrayList<>();
        URL url;
        Integer j = 1;
        while(true) {
            url = DataBase.class.getClassLoader().getResource("/data/" + folder + "/" + j + ".txt");
            if (url == null) return;
            try {
                readFile(url.getPath(), j);
            } catch (IOException e) {
                System.out.println("Couldn't find file with id: " + j);
            }
            j++;
        }
    }
    
    private void readFile(String file, Integer id) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            documents.add(new Document(stringBuilder.toString(), id));
        }
    }
    
    public Document getDocumentById(Integer id ) {
        for (Document s : documents) {
            if (s.getFileId().equals(id))
                return s;
        }  
        return null;
    }

    public ArrayList<Document> getDocuments() { return documents; }
}
