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
    devServer: {
        open: true,
        host: 'localhost',
        port: '8081',
        proxy: {
            '/api': {
                target: 'http://localhost:8080', // 要请求的地址
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}