const
    path = require('path'), htmlWebpackPlugin = require('html-webpack-plugin'),
    miniCssExtractPlugin = require('mini-css-extract-plugin'),

    // Constants
    resources = path.resolve("./src/main/resources"),
    stat = path.resolve(resources, "static"),
    src = path.resolve(stat, "src"),
    build = path.resolve(stat, "build"),

    mode = process.env.NODE_ENV === 'production' ? 'production' : 'development'


module.exports = {
    entry: path.resolve(src, "index.jsx"),
    output: {
        path: build,
        filename: 'index.min.js'
    },
    mode: mode,
    plugins: [
        new miniCssExtractPlugin({
            filename: "index.min.css"
        }),
        new htmlWebpackPlugin({
            hash: false,
            cache: mode === "production",
            template: path.resolve(src, "index.html"),
            filename: path.resolve(build, "index.min.html"),
            favicon: path.resolve(src, "media", "favicon.ico"),
            publicPath: "build",
            minify: mode === "production",
            csrf: '${_csrf.token}',
        }),
    ],
    module: {
        rules: [
            { // JavaScript
                test: /\.(js|jsx)$/,
                include: path.resolve(src),
                exclude: /node_modules/,
                use: "babel-loader"
            },
            { // Stylesheets
                test: /\.sass$/,
                use: ['style-loader',
                    miniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            modules: true,
                            sourceMap: mode !== "production",
                        },
                    },
                    {
                        loader: "sass-loader",
                        options: {
                            sassOptions: {outputStyle: "compressed"},
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
    devServer: {
        contentBase: build,
        compress: true,
        port: 8080,
        watchContentBase: true,
        progress: true
    },
}
