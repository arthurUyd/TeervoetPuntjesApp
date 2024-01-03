const {
  tables
} = require('..')

module.exports = {
  seed: async (knex) => {
    await knex(tables.badge).insert([{
        id: 1,
        titel: "Geschiedenis, Wet en Belofte",
        image_url: "",
      },
      {
        id: 2,
        titel: "Uniform",
        image_url: "",
      },
      {
        id: 3,
        titel: "Patrouilleleven",
        image_url: "",
      },
      {
        id: 4,
        titel: "Vuren",
        image_url: "",
      },
      {
        id: 5,
        titel: "Kaart en Kompas",
        image_url: "",
      },
      {
        id: 6,
        titel: "Natuur",
        image_url: "",
      },
      {
        id: 7,
        titel: "Observatie",
        image_url: "",
      },
      {
        id: 8,
        titel: "EHBO",
        image_url: "",
      },
      {
        id: 9,
        titel: "Bijl, Mes, Hamer, Schop en Zaag",
        image_url: "",
      },
      {
        id: 10,
        titel: "Knopen en sjorringen",
        image_url: "",
      },
      {
        id: 11,
        titel: "Tent",
        image_url: "",
      },
      {
        id: 12,
        titel: "Rugzak",
        image_url: "",
      },
      {
        id: 13,
        titel: "Schatten",
        image_url: "",
      },
    ])
  }
}