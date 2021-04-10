	'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let tbUsuario = sequelize.define('tbUsuario',{
		idUsuario: {
			field: 'idUsuario',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeUsuario: {
			field: 'nomeUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		emailUsuario: {
			field: 'emailUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		senhaUsuario: {
			field: 'senhaUsuario',
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		tableName: 'tbUsuario', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return tbUsuario;
};
