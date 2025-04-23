const {
  tables
} = require('..')

module.exports = {
  seed: async (knex) => {
    await knex(tables.badge).insert([{
        id: 1,
        titel: "Geschiedenis, Wet en Belofte",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Fiere Fosser copy 2.png",
        file_url:"https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-2.pdf"
      },
      {
        id: 2,
        titel: "Uniform",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Hemd.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-3.pdf"
      },
      {
        id: 3,
        titel: "Patrouilleleven",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Fluitje ",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-12.pdf"
      },
      {
        id: 4,
        titel: "Vuren",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Potje copy.png",
        file_url:"https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-7.pdf"
      },
      {
        id: 5,
        titel: "Kaart en Kompas",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Uitwisseling copy 2.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-10.pdf"
      },
      {
        id: 6,
        titel: "Natuur",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Golf.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-5.pdf"
      },
      {
        id: 7,
        titel: "Observatie",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Oog copy.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-11.pdf"
      },
      {
        id: 8,
        titel: "EHBO",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Hart copy.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-13.pdf"
      },
      {
        id: 9,
        titel: "Bijl, Mes, Hamer, Schop en Zaag",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Maan copy.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-6.pdf"
      },
      {
        id: 10,
        titel: "Knopen en sjorringen",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Slang.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-8.pdf"
      },
      {
        id: 11,
        titel: "Tent",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Tent.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-14.pdf"
      },
      {
        id: 12,
        titel: "Rugzak",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Snor copy.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-15.pdf"
      },
      {
        id: 13,
        titel: "Schatten",
        image_url: "https://teervoetappstorage.blob.core.windows.net/images/2022_Patchhuisstijl_GrafischeElementen_Anker copy 2.png",
        file_url: "https://teervoetappstorage.blob.core.windows.net/images/Techniekenbundel-part-4.pdf"
      },
    ])
  }
}