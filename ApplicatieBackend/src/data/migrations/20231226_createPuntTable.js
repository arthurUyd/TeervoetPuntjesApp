const {tables} = require('..');

module.exports = {
  up: async(knex) => {
    await knex.schema.createTable(tables.puntjes, table => {
      table.increments('id').primary();
      table.string('titel',255).notNullable();
      table.integer('badge_id').unsigned().notNullable();

      table.foreign('badge_id', "fk_puntje_badge").references(`${tables.badge}.id`).onDelete('CASCADE');
      
    });
  },
  down: (knex) => {
    return knex.schema.dropTableIfExists(tables.puntjes);
  }
};