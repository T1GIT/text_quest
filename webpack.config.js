// Plugins
const path = require('path'), htmlWebpackPlugin = require('html-webpack-plugin');
const miniCssExtractPlugin = require('mini-css-extract-plugin');
const compressionPlugin = require("compression-webpack-plugin");
const uglifyJsPlugin = require("uglifyjs-webpack-plugin")

// Paths
const resources = path.resolve("./src/main/resources");
const stat = path.resolve(resources, "static");
const src = path.resolve(stat, "src");
const build = path.resolve(stat, "build");

// Mode
const modes = {prod: "production", dev: "development"}
const mode = process.env.NODE_ENV === modes.prod ? modes.prod : modes.dev

module.exports = {
    entry: path.resolve(src, "index.jsx"),
    output: {
        path: build,
        filename: 'index.min.js'
    },
    mode: mode,
    plugins: [
        new compressionPlugin(),
        new miniCssExtractPlugin({
            filename: "index.min.css"
        }),
        new htmlWebpackPlugin({
            hash: false,
            cache: mode === modes.prod,
            template: path.resolve(src, "index.html"),
            filename: path.resolve(build, "index.min.html"),
            favicon: path.resolve(src, "media", "favicon.ico"),
            publicPath: "build",
            minify: mode === modes.prod,
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
                include: path.resolve(src, "component"),
                use: ['style-loader',
                    miniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            modules: true,
                            sourceMap: mode !== modes.prod,
                        },
                    },
                    {
                        loader: "sass-loader",
                        options: {
                            sassOptions: {outputStyle: "compressed"},
                            sourceMap: mode !== modes.prod
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
    optimization: {
        minimize: mode === "production",
        minimizer: [new uglifyJsPlugin({
            sourceMap: mode !== modes.prod,
        })],
        moduleIds: mode === modes.prod ? "size" : "named",
        mergeDuplicateChunks: true,

    },
    devServer: {
        contentBase: build,
        compress: true,
        port: 8080,
        watchContentBase: true,
        progress: true
    },
}
