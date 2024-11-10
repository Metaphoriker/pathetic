# ![Pathetic](https://github.com/patheloper/pathetic/assets/50031457/2af0e918-dd57-48aa-b8e1-87356271ac1d)

# Pathetic - A Pathfinding library for Minecraft

<center><p style="display: inline-block; vertical-align: middle;"><a href="https://discord.gg/zGx9BSzKfJ"><img src="https://github.com/user-attachments/assets/db9fa4e3-94a3-42dc-90c3-5379127120aa" width="75"></a></p>

**Pathetic** is a high-performance, backwards-compatible, and asynchronous pathfinding library designed for **Spigot**,
**Paper**, and their forks. Pathetic leverages the **A*** algorithm with customizable heuristics for real-time
pathfinding in Minecraft server environments.

Pathetic excels in handling complex terrains with features such as diagonal movement, vertical pathing, and user-defined
filters for greater flexibility.

## Key Features

- **Advanced A\* Algorithm**: Employs multiple distance metrics (Manhattan, Octile, Perpendicular) and height
  differences
  for pathfinding, optimized for 3D worlds like Minecraft.
- **Asynchronous Pathfinding**: Non-blocking operations using `CompletableFuture` to minimize server impact during
  pathfinding.
- **Fibonacci Heap for Efficient Queuing**: The open set (frontier) is managed using a **Fibonacci heap**, ensuring
  optimal node retrieval with faster `insert` and `extract min` operations.
- **Customizable Heuristics**: Fine-tune pathfinding behavior using `HeuristicWeights` for balanced navigation in any
  world configuration.
- **Regional Grid Optimization**: Uses `ExpiringHashMap` and **Bloom filters** to efficiently track explored regions,
  minimizing memory overhead.
- **Dynamic Path Filters**: Define custom filters to modify node validity or prioritize paths based on criteria such as
  passability, block type, or world boundaries.

## Installation

### Maven

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.patheloper.pathetic</groupId>
    <artifactId>pathetic-mapping</artifactId>
    <version>VERSION</version>
</dependency>
```

### Gradle

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.patheloper.pathetic:pathetic-mapping:VERSION'
}
```

## Advanced Usage: Filtering and Prioritizing Paths

Here’s how to set up and use Pathetic to find paths between random points in a Minecraft world:

```java
public class AdvancedPathExample extends JavaPlugin {

    @Override
    public void onEnable() {
        PatheticMapper.initialize(this);
        findOptimizedPath(randomLocation(), randomLocation());
    }

    @Override
    public void onDisable() {
        PatheticMapper.shutdown();
    }

    private void findOptimizedPath(PathPosition start, PathPosition end) {
        Pathfinder pathfinder = PatheticMapper.newPathfinder();
        List<PathFilter> filters = List.of(new PassablePathFilter(), new CustomHeightFilter());
        List<PathFilterStage> filterStages = List.of(new EarlyExitFilterStage());

        pathfinder
                .findPath(start, end, filters, filterStages)
                .thenAccept(
                        pathfinderResult -> {
                            if (pathfinderResult.getPathState() == PathState.FOUND) {
                                pathfinderResult
                                        .getPath()
                                        .forEach(
                                                location ->
                                                        player.sendBlockChange(
                                                                location, Material.GOLD_BLOCK.createBlockData()));
                            } else {
                                getLogger().info("Pathfinding failed or exceeded limits.");
                            }
                        });
    }

    private PathPosition randomLocation() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new PathPosition(random.nextInt(0, 100), random.nextInt(0, 100), random.nextInt(0, 100));
    }
}
```

## Documentation:

- **Javadocs**: [View Javadocs](https://javadocs.pathetic.ollieee.xyz/)
- **API Documentation**: [Access our Docs](https://docs.pathetic.ollieee.xyz/)

## License:

Pathetic is released under the GPL License.

## Contributions:

We welcome contributions! Feel free to fork the repository and submit pull requests. For major changes, open an issue
first to discuss what you’d like to change.

## Support:

For help and support, join our community on
the [SpigotMC forum thread](https://www.spigotmc.org/threads/how-pathetic.578998/)
or [Discord Server](https://discord.gg/HMqCbdQjX9).

## Sponsored By:

<img src="https://github.com/user-attachments/assets/262672b9-a673-4732-8392-5771e7aadfd0" alt="JetBrains_beam_logo" width="400"/>

We'd like to thank JetBrains for sponsoring the development of this project with an amazing toolset!
