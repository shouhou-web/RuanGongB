module.exports = {
    configureWebpack: {
        resolve: {
            alias:{
                'assets':'@/assets',
                'common':'@/common',
                'conponents':'@/conponents',
                'network':'@/network',
                'views':'@/views',
            }
        }
    }
}