<img src="https://github.com/user-attachments/assets/2f5335e3-b095-4e2f-a9de-e3ac46fbaf45" alt="Transparent" width="100" height="100" align="right" />
<br><br>

<h1>Pathetic - A Pathfinding library for Minecraft</h1>

<p>A high-performance, backwards-compatible, and asynchronous easy-to-use pathfinding library written in Java for 3D environments.
<br> Pathetic leverages the <b>A* algorithm</b> with customizable heuristics for real-time pathfinding.</p>

<h2>Key Features</h2>

<ul>
  <li><b>Advanced A* Algorithm:</b> Employs multiple distance metrics (Manhattan, Octile, Perpendicular) and height differences for pathfinding, optimized for 3D worlds like Minecraft.</li>
  <li><b>Asynchronous Pathfinding:</b> Non-blocking operations using <code>CompletableFuture</code> to minimize server impact during pathfinding.</li>
  <li><b>Fibonacci Heap for Efficient Queuing:</b> The open set (frontier) is managed using a <b>Fibonacci heap</b>, ensuring optimal node retrieval with faster <code>insert</code> and <code>extract min</code> operations.</li>
  <li><b>Customizable Heuristics:</b> Fine-tune pathfinding behavior using <code>HeuristicWeights</code> for balanced navigation in any world configuration.</li>
  <li><b>Regional Grid Optimization:</b> Uses <code>ExpiringHashMap</code> and <b>Bloom filters</b> to efficiently track explored regions, minimizing memory overhead.</li>
  <li><b>Dynamic Path Filters:</b> Define custom filters to modify node validity or prioritize paths based on criteria such as passability, block type, or world boundaries.</li>
</ul>

<h2>Showcase</h2>

![ezgif-3-caa688a773](https://github.com/user-attachments/assets/ab243485-f122-4067-bab0-a5ed97b717c1)

<h2>Installation</h2>

To integrate pathetic into your bukkit project, follow the following steps:
<br>
*(We advise you to relocate pathetic)*

<h3>Maven</h3>

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Metaphoriker.pathetic</groupId>
    <artifactId>pathetic-bukkit</artifactId>
    <version>VERSION</version>
</dependency>
```

<h3>Gradle</h3>

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.Metaphoriker.pathetic:pathetic-bukkit:VERSION'
}
```

<h3>Manual Pathetic</h3>
If you want to leverage pathetic for your own implementation, you can use the following dependency:

<h4>Maven</h4>
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>de.metaphoriker</groupId>
    <artifactId>pathetic-engine</artifactId>
    <version>VERSION</version>
</dependency>
```

<h4>Gradle</h4>
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'de.metaphoriker:pathetic-engine:VERSION'
}
```
<h2>Example Usage</h2>

<p>See the <a href="https://github.com/Metaphoriker/pathetic/tree/trunk/pathetic-example">pathetic-example</a> module for a detailed bukkit example.</p>

<h2>Documentation</h2>

<p><a href="https://javadocs.pathetic.ollieee.xyz/">View Javadocs</a></p>

<h2>Contributions</h2>
We welcome contributions! Feel free to fork the repository and submit pull requests. For major changes, open an issue first to discuss what you'd like to change.
<br><br>

Special thanks to [@Ollie](https://github.com/olijeffers0n), the co-founder of pathetic, who helped building up pathetic in the very beginning!

<h2>Discord Server</h2>
<p>Join our Discord server to get in touch!
<br><br>

<a href="https://discord.gg/zGx9BSzKfJ">
  <img src="https://github.com/user-attachments/assets/e99713a7-dd87-4b67-a86e-6b21e6ba1f91" width="75" style="vertical-align: middle;" />
</a>
</p>

<h2>Sponsored By</h2>
<p>Pathetic is sponsored by JetBrains.
<br><br>

<img src="https://github.com/user-attachments/assets/262672b9-a673-4732-8392-5771e7aadfd0" alt="JetBrains_beam_logo" width="100"/>
</p>
