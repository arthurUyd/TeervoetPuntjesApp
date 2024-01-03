const {tables} = require('..');

module.exports = {
  seed: async(knex) => {
    await knex(tables.gebruiker).insert([
      {
        naam: "lid1",
        email: "lid1@gmail.com",
        password: "lid",
        isLeider: false,
      }, {
        naam: "lid2",
        email: "lid2@gmail.com",
        password: "lid",
        isLeider: false,
      },
      {
        naam: "leider",
        email: "leider@gmail.com",
        password: "leider",
        isLeider: true,
      },
    ]);
  }
};