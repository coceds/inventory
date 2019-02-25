package capstone.inventory.manager;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductLoader {

    public List<String> loadProducts() {
        try (
                InputStream inputStream = new ClassPathResource("data.csv").getInputStream();
                Reader targetReader = new InputStreamReader(inputStream);
        ) {
            CsvReader csvReader = new CsvReader();
            csvReader.setContainsHeader(true);
            CsvContainer csv = csvReader.read(targetReader);
            List<CsvRow> rows = csv.getRows();
            return rows.stream()
                    .map(r -> r.getField(0))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
