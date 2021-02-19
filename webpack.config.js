// Plugins
const Path = require('path'), htmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const CompressionPlugin = require("compression-webpack-plugin")
const UglifyJsPlugin = require("uglifyjs-webpack-plugin")
const CssnanoPlugin = require("cssnano-webpack-plugin")
const CleanWebpackPlugin = require("clean-webpack-plugin").CleanWebpackPlugin
const Webpack = require("webpack")

// Paths
const dir = {}
dir.res = Path.resolve("./src/main/resources");
dir.stat = Path.resolve(dir.res, "static");
dir.src = Path.resolve(dir.stat, "src");
dir.build = Path.resolve(dir.stat, "build");
name = "index"

// Mode
const modes = {prod: "production", dev: "development"}
const mode = process.env.NODE_ENV === modes.prod ? modes.prod : modes.dev

module.exports = {
    entry: Path.resolve(dir.src, "index.jsx"),
    output: {
        path: dir.build,
        filename: name + ".min.js"
    },
    mode: mode,
    plugins: [
        new Webpack.ProgressPlugin(),
        new CleanWebpackPlugin(),
        new CompressionPlugin(),
        new MiniCssExtractPlugin({
            filename: name + ".min.css"
        }),
        new htmlWebpackPlugin({
            hash: true,
            cache: mode === modes.prod,
            template: Path.resolve(dir.src, "index.html"),
            filename: Path.resolve(dir.build, name + ".min.html"),
            favicon: Path.resolve(dir.src, "media", "favicon.ico"),
            publicPath: "build",
            minify: mode === modes.prod,
            csrf: '${_csrf.token}',
        }),
    ],
    module: {
        rules: [
            { // JavaScript
                test: /\.(js|jsx)$/,
                include: Path.resolve(dir.src),
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ["@babel/env", "@babel/react"],
                        plugins: ["@babel/plugin-proposal-class-properties"]
                    }
                }
            },
            { // Stylesheets
                test: /\.sass$/,
                include: Path.resolve(dir.src, "component"),
                use: ['style-loader',
                    MiniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            modules: true,
                            sourceMap: mode !== modes.prod,
                        },
                    },
                    {
                        loader: 'postcss-loader',
                        options: {
                            postcssOptions: {
                                plugins: [
                                    require('cssnano')(),
                                    require('css-mqpacker')(),
                                    require('autoprefixer')({
                                        'overrideBrowserslist': ['> 1%', 'last 2 versions']
                                    }),
                                ],
                            }
                        }
                    },
                    {
                        loader: "sass-loader",
                        options: {
                            sourceMap: mode !== modes.prod
                        }
                    }
                ]
            },
            { // Images
                test: /.(png|svg|jpg|gif)$/,
                include: Path.resolve(dir.src, "media"),
                exclude: /node_modules/,
                use: ["file-loader"]
            },
        ]
    },
    optimization: {
        minimize: mode === modes.prod,
        minimizer: [
            new UglifyJsPlugin({sourceMap: false}),
            new CssnanoPlugin({
                sourceMap: false,
                cssnanoOptions: {
                    preset: ['default', {
                        discardComments: {removeAll: true}
                    }]
                }
            })
        ],
        moduleIds: mode === modes.prod ? "size" : "named",
        mergeDuplicateChunks: true,

    },
    devServer: {
        contentBase: dir.build,
        compress: true,
        port: 8080,
        watchContentBase: true,
        progress: true
    },
}
