'use strict'
const utils = require('./utils')
const webpack = require('webpack')
const config = require('../config')
const merge = require('webpack-merge')
const path = require('path')
const baseWebpackConfig = require('./webpack.base.conf')
const CopyWebpackPlugin = require('copy-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const FriendlyErrorsPlugin = require('friendly-errors-webpack-plugin')
const portfinder = require('portfinder')

function resolve (dir) {
  return path.join(__dirname, '..', 'WEB-INF/pages/' + dir)
}

const HOST = process.env.HOST
const PORT = process.env.PORT && Number(process.env.PORT)

const devWebpackConfig = merge(baseWebpackConfig, {
  module: {
    rules: utils.styleLoaders({ sourceMap: config.dev.cssSourceMap, usePostCSS: true })
  },
  // cheap-module-eval-source-map is faster for development
  devtool: config.dev.devtool,

  // these devServer options should be customized in /config/index.js
  devServer: {
    clientLogLevel: 'warning',
    historyApiFallback: {
      rewrites: [
        { from: /.*/, to: path.posix.join(config.dev.assetsPublicPath, 'index.html') },
      ],
    },
    hot: true,
    contentBase: false, // since we use CopyWebpackPlugin.
    compress: true,
    host: HOST || config.dev.host,
    port: PORT || config.dev.port,
    open: config.dev.autoOpenBrowser,
    overlay: config.dev.errorOverlay
      ? { warnings: false, errors: true }
      : false,
    publicPath: config.dev.assetsPublicPath,
    proxy: config.dev.proxyTable,
    quiet: true, // necessary for FriendlyErrorsPlugin
    watchOptions: {
      poll: config.dev.poll,
    }
  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env': require('../config/dev.env')
    }),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NamedModulesPlugin(), // HMR shows correct file names in console on update.
    new webpack.NoEmitOnErrorsPlugin(),
    // https://github.com/ampedandwired/html-webpack-plugin
    new HtmlWebpackPlugin({
      filename: 'index.html',
      template: resolve('index.html'),
      inject: true,
      chunks: ['app']
    }),

    new HtmlWebpackPlugin({
      filename: 'login.html',
      template: resolve('login.html'),
      inject: true,
      chunks: ['login']
    }),
    new HtmlWebpackPlugin({
      filename: 'register.html',
      template: resolve('register.html'),
      inject: true,
      chunks: ['register']
    }),
    new HtmlWebpackPlugin({
      filename: 'contactus.html',
      template: resolve('contactus.html'),
      inject: true,
      chunks: ['contactus']
    }),
    new HtmlWebpackPlugin({
      filename: 'moreNews.html',
      template: resolve('moreNews.html'),
      inject: true,
      chunks: ['moreNews']
    }),
    new HtmlWebpackPlugin({
      filename: 'details.html',
      template: resolve('details.html'),
      inject: true,
      chunks: ['details']
    }),
    new HtmlWebpackPlugin({
      filename: 'goodsDetails.html',
      template: resolve('goodsDetails.html'),
      inject: true,
      chunks: ['goodsDetails']
    }),
    new HtmlWebpackPlugin({
      filename: 'collection.html',
      template: resolve('collection.html'),
      inject: true,
      chunks: ['collection']
    }),
    new HtmlWebpackPlugin({
      filename: 'registerSuccess.html',
      template: resolve('registerSuccess.html'),
      inject: true,
      chunks: ['registerSuccess']
    }),
    new HtmlWebpackPlugin({
      filename: 'goodsSearch.html',
      template: resolve('goodsSearch.html'),
      inject: true,
      chunks: ['goodsSearch']
    }),
    new HtmlWebpackPlugin({
      filename: 'adminLogin.html',
      template: resolve('adminLogin.html'),
      inject: true,
      chunks: ['adminLogin']
    }),
    // copy custom static assets
    new CopyWebpackPlugin([
      {
        from: path.resolve(__dirname, '../static'),
        to: config.dev.assetsSubDirectory,
        ignore: ['.*']
      }
    ])
  ]
})

module.exports = new Promise((resolve, reject) => {
  portfinder.basePort = process.env.PORT || config.dev.port
  portfinder.getPort((err, port) => {
    if (err) {
      reject(err)
    } else {
      // publish the new Port, necessary for e2e tests
      process.env.PORT = port
      // add port to devServer config
      devWebpackConfig.devServer.port = port

      // Add FriendlyErrorsPlugin
      devWebpackConfig.plugins.push(new FriendlyErrorsPlugin({
        compilationSuccessInfo: {
          messages: [`Your application is running here: http://${devWebpackConfig.devServer.host}:${port}`],
        },
        onErrors: config.dev.notifyOnErrors
        ? utils.createNotifierCallback()
        : undefined
      }))

      resolve(devWebpackConfig)
    }
  })
})
