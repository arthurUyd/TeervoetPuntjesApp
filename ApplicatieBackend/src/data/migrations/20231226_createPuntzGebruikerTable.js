const {
  tables
} = require('..');

module.exports = {
  up: async (knex) => {
    await knex.schema.createTable(tables.gebruiker_puntjes, (table) => {
      table.increments('id').primary();
      table.integer('user_id').unsigned().references('id').inTable(tables.gebruiker);
      table.integer('punt_id').unsigned().references('id').inTable(tables.puntjes);
      // Add other columns as needed
      
    });
  },

  down: async (knex) => {
    await knex.schema.dropTableIfExists(tables.gebruiker_puntjes);
  }

}