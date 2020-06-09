module.exports = {
    configureWebpack: {
        resolve: {
            alias: {
                'assets': '@/assets',
                'common': '@/common',
                'conponents': '@/conponents',
                'network': '@/network',
                'views': '@/views',
            }
        }
    },
    publicPath: '/',
    outputDir: 'dist',
    pwa: {
        iconPaths: {
            favicon32: 'favicon.ico',
            favicon16: 'favicon.ico',
            appleTouchIcon: 'favicon.ico',
            maskIcon: 'favicon.ico',
            msTileImage: 'favicon.ico'
        }
    }
    // devServer: {
    //     open: true,
    //     host: 'localhost',
    //     port: '8081',
    //     proxy: {
    //         '/api': {
    //             target: 'http://39.99.154.244:8080', // 要请求的地址
    //             ws: true,
    //             changeOrigin: true,
    //             pathRewrite: {
    //                 '^/api': ''
    //             }
    //         }
    //     }
    // }
}