package de.imolli;

import org.bukkit.Bukkit;
import org.bukkit.util.CachedServerIcon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.stream.Stream;

public class IconRepository {

    private static IconRepository instance;
    private final List<CachedServerIcon> icons = new ArrayList<>();

    public static IconRepository getInstance() {
        if (instance == null) {
            instance = new IconRepository();
        }

        return instance;
    }

    public CachedServerIcon getRandomIcon() {
        final int randomElementIndex = ThreadLocalRandom.current().nextInt(icons.size()) % icons.size();

        return icons.get(randomElementIndex);
    }

    public void loadIcons() {
        try {
            final URI iconsFolder = Objects.requireNonNull(getClass().getResource("/icons")).toURI();
            final FileSystem fileSystem = FileSystems.newFileSystem(iconsFolder, Collections.emptyMap());
            final Stream<Path> pathStream = Files.walk(fileSystem.getPath("/icons"), 1);

            pathStream.forEach(this::loadIcon);

            pathStream.close();
            fileSystem.close();

            LeagueServerIconPlugin.getPluginLogger().log(Level.INFO, "Loaded " + icons.size() + " champion icons.");
        } catch (Exception e) {
            LeagueServerIconPlugin.getPluginLogger().log(Level.SEVERE, "Error while reading server icons", e);
        }
    }

    public void loadIcon(Path a) {
        try {
            // We need to skip the parent directory itself
            if (Files.isDirectory(a)) {
                return;
            }

            final InputStream inputStream = Files.newInputStream(a);
            final BufferedImage read = ImageIO.read(inputStream);
            final CachedServerIcon cachedServerIcon = Bukkit.getServer().loadServerIcon(read);

            icons.add(cachedServerIcon);
        } catch (Exception e) {
            LeagueServerIconPlugin.getPluginLogger().log(Level.SEVERE, "Error while reading server icon", e);
        }
    }

}
