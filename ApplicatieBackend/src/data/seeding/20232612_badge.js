const {
  tables
} = require('..')

module.exports = {
  seed: async (knex) => {
    await knex(tables.badge).insert([{
        id: 1,
        titel: "Geschiedenis, Wet en Belofte",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Fiere Fosser copy 2.png",
      },
      {
        id: 2,
        titel: "Uniform",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Hemd.png",
      },
      {
        id: 3,
        titel: "Patrouilleleven",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Fluitje ",
      },
      {
        id: 4,
        titel: "Vuren",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Potje copy.png",
      },
      {
        id: 5,
        titel: "Kaart en Kompas",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Uitwisseling copy 2.png",
      },
      {
        id: 6,
        titel: "Natuur",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Golf.png",
      },
      {
        id: 7,
        titel: "Observatie",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Oog copy.png",
      },
      {
        id: 8,
        titel: "EHBO",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Hart copy.png",
      },
      {
        id: 9,
        titel: "Bijl, Mes, Hamer, Schop en Zaag",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Maan copy.png",
      },
      {
        id: 10,
        titel: "Knopen en sjorringen",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Slang.png",
      },
      {
        id: 11,
        titel: "Tent",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Tent.png",
      },
      {
        id: 12,
        titel: "Rugzak",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Snor copy.png",
      },
      {
        id: 13,
        titel: "Schatten",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Anker copy 2.png",
      },
    ])
  }
}