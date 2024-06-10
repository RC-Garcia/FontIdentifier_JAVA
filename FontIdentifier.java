import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FontIdentifier {

    private static final String FONT_MATCHER_API_URL = "https://api.fontmatcher.com/v1/match"; // Replace with actual API URL
    private static final String FONT_MATCHER_API_KEY = "YOUR_API_KEY"; // Replace with your API key

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to the image file.");
            return;
        }

        String imagePath = args[0];
        try {
            // Step 1: Extract text from the image using Tesseract OCR
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("tessdata"); // Path to tessdata directory
            BufferedImage image = ImageIO.read(new File(imagePath));
            String extractedText = tesseract.doOCR(image);

            // Step 2: Identify the font using FontMatcher API
            String font = identifyFont(extractedText);
            System.out.println("Identified Font: " + font);

        } catch (IOException | TesseractException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String identifyFont(String text) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(FONT_MATCHER_API_URL);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", "Bearer " + FONT_MATCHER_API_KEY);

            JSONObject json = new JSONObject();
            json.put("text", text);

            StringEntity entity = new StringEntity(json.toString());
            request.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(request);
            String responseString = EntityUtils.toString(response.getEntity());

            JSONObject jsonResponse = new JSONObject(responseString);
            return jsonResponse.getString("font");

        } catch (IOException e) {
            System.err.println("Error identifying font: " + e.getMessage());
            return "Unknown";
        }
    }
}
