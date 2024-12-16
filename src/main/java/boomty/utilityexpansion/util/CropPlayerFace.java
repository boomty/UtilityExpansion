package boomty.utilityexpansion.util;

import com.mojang.authlib.properties.Property;
import net.minecraft.world.entity.player.Player;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 Crops player's face texture from player's skin
 **/
public class CropPlayerFace {
    private final Player player;

    public CropPlayerFace(Player player){
        this.player = player;
    }

    // download player's skin if network connection exists
    private void downloadSkin() throws IOException {
        Property property = player.getGameProfile().getProperties().get("textures").iterator().next();
        String texture = property.getValue();

        String decodedTexture = new String(Base64.getDecoder().decode(texture), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(decodedTexture);
        String imageUrl = rootNode.path("textures").path("SKIN").path("url").asText();

        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.connect();
        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            BufferedImage skinImage = ImageIO.read(connection.getInputStream());

            File outputfile = new File("src/main/resources/assets/utilityexpansion/textures/skins/saved/player_skin.png");
            ImageIO.write(skinImage, "png", outputfile);
        } else {
            throw new IOException("HTTP error code: " + responseCode);
        }
    }

    // crop player's skin for the face texture
    private void imageCropper() throws IOException {
        try {
            File inputFile = new File("src/main/resources/assets/utilityexpansion/textures/skins/saved/player_skin.png");
            BufferedImage originalImage = ImageIO.read(inputFile);

            BufferedImage croppedImage = originalImage.getSubimage(8, 8, 8, 8);

            File outputFile = new File("src/main/resources/assets/utilityexpansion/textures/skins/saved/player_face.png");
            ImageIO.write(croppedImage, "png", outputFile);
        }
        catch (IOException e) {
            System.err.println("Error processing the image: " + e.getMessage());
        }
    }

    private void imagePaster() throws IOException {

    }

    public void cropPlayerSkin() throws IOException {
        downloadSkin();
        imageCropper();
    }
}
