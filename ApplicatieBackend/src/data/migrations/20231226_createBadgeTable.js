const {tables} = require('..');

module.exports = {
  up: async (knex) => {
    await knex.schema.createTable(tables.badge, (table) => {
      table.increments('id').primary();
      table.string('titel').notNullable();
      table.string('image_url').notNullable();
      table.string('file_url').notNullable();
    });
  },

  down: async (knex) => {
    await knex.schema.dropTableIfExists(tables.badge);
  },
}