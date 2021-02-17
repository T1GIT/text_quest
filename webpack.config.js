const
    // Modules
    path = require('path'),
    HtmlWebpackPlugin = require('html-webpack-plugin'),
    webpack = require('webpack'),
    compressionPlugin = require("compression-webpack-plugin"),

    // Constants
    root = "./src/main/resources/static",
    src = `${root}/src`,
    dest = `${root}/build`


console.log(__dirname)
module.exports = {
    entry: path.resolve(__dirname, `${src}/react/index.js`),
    module: {
        rules: [
            {test: new RegExp(`${src}/\\.svg$`, 'g'), use: 'svg-inline-loader'},
            {test: new RegExp(`${src}/\\.css$`, 'g'), use: ['style-loader', 'css-loader']},
            {test: new RegExp(`${src}/\\.js$|\\.jsx$`, 'g'), use: 'babel-loader'}
        ]
    },
    output: {
        path: path.resolve(__dirname, dest),
        filename: 'index_bundle.js'
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, `${root}/templates`), // шаблон
            filename: 'index.html', // название выходного файла
        }),
        // new webpack.optimize.UglifyJsPlugin({
        //     beautify: false,
        //     comments: false,
        //     compress: {
        //         sequences     : true,
        //         booleans      : true,
        //         loops         : true,
        //         unused      : true,
        //         warnings    : false,
        //         drop_console: true,
        //         unsafe      : true
        //     }
        // }),
        // new compressionPlugin({
        //     asset: "[path].gz[query]",
        //     algorithm: "gzip",
        //     test: new RegExp(`${dest}/\\.js$|\\.html$`, 'g'),
        //     threshold: 10240,
        //     minRatio: 0.8
        // })
    ],
    mode: process.env.NODE_ENV === 'production' ? 'production' : 'development'
}


// var packageJSON = require('./package.json');
// var path = require('path');
// var webpack = require('webpack');
// module.exports = {
//     devtool: 'source-map',
//     entry: './index.js',
//     output: {
//         path: path.join(__dirname, 'generated'),
//         filename: 'app-bundle.js'},
//     resolve: {extensions: ['.js', '.jsx']},
//     plugins: [
//         new webpack.LoaderOptionsPlugin({
//             debug: true}),
//         new webpack.DefinePlugin({
//             "process.env": {
//                 NODE_ENV: JSON.stringify("development")
//             }
//         })
//     ],
//     module: {
//         rules: [
//             {
//                 test: /\.jsx?$/,
//                 loader: 'babel-loader',
//                 exclude: /node_modules/
//             }
//         ]
//     },
//     devServer: {
//         overlay: true,
//         open: true,
//         noInfo: false,
//         quiet: false,
//         lazy: false,
//         watchOptions: {
//             poll: true
//         }
//     }
// }