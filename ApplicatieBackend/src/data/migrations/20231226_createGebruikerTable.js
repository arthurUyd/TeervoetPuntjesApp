const {tables} = require('..');

module.exports = {
  up: async(knex) => {
    await knex.schema.createTable(tables.gebruiker, table => {
      table.increments('id').primary();
      table.string('naam',255).notNullable();
      table.string('email',255).notNullable();
      table.string('password',255).notNullable();
      table.boolean('isLeider').defaultTo(false);
      
      table.unique('email', 'un_email');
      
    });
  },
  down: (knex) => {
    return knex.schema.dropTableIfExists(tables.gebruiker);
  }
};