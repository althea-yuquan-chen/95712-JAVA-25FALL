module com.java25fall {
    // 1. 引入需要的 JavaFX 模块
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics; // 有时需要显式传递

    // 2. 开放你的包给 JavaFX (用于反射加载 FXML 和主类)
    // "opens" 允许 JavaFX 读取你的私有字段（FXML注入需要）
    opens com.java25fall to javafx.fxml;

    // 3. 导出你的包，让外部（如 JavaFX 启动器）能访问你的类
    exports com.java25fall;
}