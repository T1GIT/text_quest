const path = require('path'), htmlWebpackPlugin = require('html-webpack-plugin'),

    // Constants
    resources = path.resolve("./src/main/resources"),
    stat = path.resolve(resources, "static"),
    src = path.resolve(stat, "src"),
    build = path.resolve(stat, "build"),

    mode = process.env.NODE_ENV === 'production' ? 'production' : 'development'


module.exports = {
    entry: {
        main: path.resolve(src, "index.jsx")
    },
    output: {
        path: path.resolve(__dirname, build),
        filename: 'index.min.js'
    },
    mode: mode,
    module: {
        rules: [
            { // JavaScript
                test: /\.(js|jsx)$/,
                include: path.resolve(),
                exclude: /node_modules/,
                use: "babel-loader"
            },
            { // Stylesheets
                test: /\.sass$/,
                use: ['style-loader',
                    {
                        loader: "css-loader",
                        options: {
                            modules: true,
                            sourceMap: mode !== "production"
                        },
                    },
                    {
                        loader: "sass-loader",
                        options: {
                            sassOptions: {
                                outputStyle: "compressed"
                            },
                            sourceMap: mode !== "production"
                        }
                    }
                ]
            },
            { // Images
                test: /.(png|svg|jpg|gif)$/,
                include: path.resolve(src, "media"),
                exclude: /node_modules/,
                use: ["file-loader"]
            },
        ]
    },
    plugins: [
        new htmlWebpackPlugin({
            template: path.resolve(src, "index.html"),
            filename: path.resolve(build, "index.min.html"),
            // favicon: "src/media/favicon.ico", TODO: Add favicon
            inject: "body",
            publicPath: "build",
            minify: mode === "production",
            csrf: '${_csrf.token}',
        }),
    ],
    devServer: {
        contentBase: build,
        compress: true,
        port: 8080,
        watchContentBase: true,
        progress: true
    },
}
