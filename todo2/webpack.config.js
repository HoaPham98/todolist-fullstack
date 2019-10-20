
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const HtmlWebPackPlugin = require("html-webpack-plugin");
module.exports = {
	mode: "development",
	entry: {
		app: ["./src/main.js"]
	},
	
	devServer: {
    headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
		"Access-Control-Allow-Headers": "*",
		https:true
    },

    contentBase: __dirname,
	port: 3000,
    proxy: {
		"/api": {
		  "target": "http://localhost:8080",
		  "ws": true
		}
	  },
		
	host:'localhost'
	
	},
	output: {
		path: __dirname + "/dist",
		filename: "main.bundle.js"
	},
	module: {
		rules: [{
			test: /\.(js|jsx)$/,
			use: ['babel-loader']
		}, {
			test: /\.css$/,
			use: [
          		MiniCssExtractPlugin.loader,
          		"css-loader", "postcss-loader",
          	]
		}],
	},
	 plugins: [
		new MiniCssExtractPlugin({
	      filename: "styles.css",
	      chunkFilename: "styles.css"
	    }),
	    new HtmlWebPackPlugin({
	    template: "./index.html",
	    filename: "./index.html"
	  })
	]
}