package utilities;

import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class CSVDataProviders {
    @DataProvider(name = "readCSV")
    public static Object[][] csvReader(Method method) {
        List<Object[]> list = new ArrayList<>();
        String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator + method.getName() + ".csv";
        File file = new File(pathname);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine(); // consume and discard the header row
            while ((line = reader.readLine()) != null) {
                list.add(line.split(",", 6));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not read " + pathname + " file.", e);
        }

        Object[][] data = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
        return data;
    }
}
