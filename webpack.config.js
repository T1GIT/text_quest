// Plugins
const Path = require('path'), htmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const CompressionPlugin = require("compression-webpack-plugin")
const UglifyJsPlugin = require("uglifyjs-webpack-plugin")
const CssnanoPlugin = require("cssnano-webpack-plugin")
const CleanWebpackPlugin = require("clean-webpack-plugin").CleanWebpackPlugin
const Webpack = require("webpack")
const SpriteLoaderPlugin = require("svg-sprite-loader/plugin")

// Paths
const dir = {}
dir.res = Path.resolve("./src/main/resources");
dir.stat = Path.resolve(dir.res, "static");
dir.src = Path.resolve(dir.stat, "src");
dir.build = Path.resolve(dir.stat, "build");

// Mode
const modes = {prod: "production", dev: "development"}
const mode = process.env.NODE_ENV === modes.prod ? modes.prod : modes.dev

module.exports = {
    entry: Path.resolve(dir.src, "index.jsx"),
    output: {
        path: dir.build,
        filename: "index.min.js"
    },
    mode: mode,
    watchOptions: {
        ignored: /node_modules/,
        aggregateTimeout: 500,
    },
    plugins: [
        new Webpack.ProgressPlugin(),
        new CleanWebpackPlugin(),
        new CompressionPlugin(),
        new SpriteLoaderPlugin(),
        new MiniCssExtractPlugin({
            filename: "index.min.css"
        }),
        new htmlWebpackPlugin({
            hash: mode === modes.prod,
            cache: mode === modes.prod,
            template: Path.resolve(dir.src, "index.html"),
            filename: Path.resolve(dir.build, "index.min.html"),
            favicon: Path.resolve(dir.src, "resources", "favicon.ico"),
            publicPath: "build",
            minify: mode === modes.prod,
        }),
    ],
    module: {
        rules: [
            { // JavaScript
                test: /\.(js|jsx)$/,
                include: dir.src,
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
                test: /\.(sass|css)$/,
                include: dir.src,
                use: [
                    MiniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            modules: {
                                localIdentName: "[name]__[local]___[hash:base64:5]"
                            },
                            importLoaders: 1,
                            sourceMap: mode !== modes.prod,
                        },
                    },
                    {
                        loader: 'postcss-loader',
                        options: {
                            postcssOptions: {
                                ident: 'postcss',
                                plugins: [
                                    require('cssnano')(),
                                    require('css-mqpacker')(),
                                    require('postcss-flexbugs-fixes'),
                                    require('autoprefixer')({
                                        'overrideBrowserslist': [
                                            'last 15 versions',
                                            '> 1%',
                                            'not ie < 9'
                                        ],
                                        flexbox: 'no-2009',
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
                test: /.(png|jpg|gif|ico)$/,
                include: dir.src,
                use: [
                    "file-loader",
                    {
                        loader: "image-webpack-loader",
                        options: {
                            mozjpeg: {progressive: true},
                            optipng: {enabled: false},
                            pngquant: {quality: [0.65, 0.90], speed: 4},
                            gifsicle: {interlaced: false},
                            webp: {quality: 75}
                        }
                    }
                ]
            },
            { // Svg
                test: /\.svg$/,
                include: dir.src,
                use: [
                    {
                        loader: 'svg-sprite-loader',
                        options: {
                            extract: true,
                            spriteFilename: "sprites.svg"
                        }
                    },
                    "svgo-loader"
                ]
            }
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
    }
}
