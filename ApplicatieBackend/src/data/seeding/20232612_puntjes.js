const {
  tables
} = require('..')

module.exports = {
  seed: async (knex) => {
    await knex(tables.puntjes).insert([{
        titel: "Bespreek beknopt het leven van BP",
        badge_id: 1,
      },
      {
        titel: "Scoutsgroet en zijn betekenis kennen ",
        badge_id: 1,
      }, {
        titel: "Linkerhanddruk en zijn betekenis kennen",
        badge_id: 1,
      }, {
        titel: "Wet en zijn betekenis kennen en naleven",
        badge_id: 1,
      }, {
        titel: "Ezelsbruggetje EENAH GO VIJZ kennen ",
        badge_id: 1,
      }, {
        titel: "Belofte en zijn betekenis kennen en naleven",
        badge_id: 1,
      }, {
        titel: "FOS-kenspreuk en -kenteken kennen",
        badge_id: 1,
      }, {
        titel: "Correcte uniform kennen en dragen",
        badge_id: 2,
      }, {
        titel: "Weten waarom het uniform gedragen wordt",
        badge_id: 2,
      }, {
        titel: "Plaats en betekenis van de kentekens kennen",
        badge_id: 2,
      },
      {
        titel:"Troepsignalen kennen voor aandacht, verzameling…",
        badge_id: 3,
      }, {
        titel:"Stomme bevelen kennen",
        badge_id: 3,
      }, {
        titel:"Organigram van de eenheid kennen",
        badge_id: 3,
      }, {
        titel:"Patrouilledier kennen en er iets over kunnen vertellen",
        badge_id: 3,
      }, {
        titel:"De functie van alle patrouilleleden kunnen beschrijven",
        badge_id: 3,
      }, {
        titel:"Weten waar een vuur mag aangelegd worden",
        badge_id: 4,
      },{
        titel:"Veiligheidsmaatregelen rond vuur kennen",
        badge_id: 4,
      },{
        titel:"Een vuur veilig kunnen doven",
        badge_id: 4,
      },{
        titel:"Vuur kunnen maken met max. 5 lucifers",
        badge_id: 4,
      },{
        titel:"Een gasvuurtje kunnen aansteken",
        badge_id: 4,
      },{
        titel:"Weten wat een topografische kaart is",
        badge_id: 5,
      },{
        titel:"Kaartsymbolen kennen adhv legende",
        badge_id: 5,
      },{
        titel:"Metrische en grafische schaal kunnen gebruiken",
        badge_id: 5,
      },{
        titel:"Windrichtingen en bijhorende graden kunnen benoemen",
        badge_id: 5,
      },{
        titel:"Kaart kunnen oriënteren a.h.v. kompas en omgeving",
        badge_id: 5,
      },{
        titel:"Topografische kaart kunnen gebruiken",
        badge_id: 5,
      },{
        titel:"Natuur niet bevuilen",
        badge_id: 6,
      },{
        titel:"Veel voorkomende planten en bomen herkennen",
        badge_id: 6,
      },{
        titel:"Giftige planten en zwammen herkennen",
        badge_id: 6,
      },{
        titel:"Weten hoe je een teek voorkomt en waar ze voorkomen",
        badge_id: 6,
      },{
        titel:"Onderscheid kennen tussen dood/levend/sjor/brandhout",
        badge_id: 6,
      },{
        titel:"Spoortekens en hun betekenis kennen en gebruiken",
        badge_id: 7,
      },{
        titel:"Voldoen aan minstens 1 kim (geur-, hoor-, smaak-, zicht-)",
        badge_id: 7,
      },{
        titel:"Weten hoe te handelen bij een zwaar ongeval",
        badge_id: 8,
      },{
        titel:"Noodnummers kennen en een gesprek kunnen voeren",
        badge_id: 8,
      },{
        titel:"Eenvoudige wonden verzorgen (schaaf-, snij-, splinter",
        badge_id: 8,
      },{
        titel:"Blaren kunnen voorkomen en verzorgen",
        badge_id: 8,
      },{
        titel:"Een bloeding kunnen stelpen",
        badge_id: 8,
      },{
        titel:"Een draagdoek kunnen maken",
        badge_id: 8,
      },{
        titel:"De kleurencode van de takken kennen",
        badge_id: 9,
      },{
        titel:"Correct gebruik en onderhoud van een schop",
        badge_id: 9,
      },{
        titel:"Correct gebruik en onderhoud van een bijl ",
        badge_id: 9,
      },{
        titel:"Correct gebruik en onderhoud van een (zak)mes",
        badge_id: 9,
      },{
        titel:"Correct gebruik en onderhoud van een zaag",
        badge_id: 9,
      },{
        titel:"Weten hoe een bijl te dragen en door te geven",
        badge_id: 9,
      },{
        titel:"Weten hoe te klieven ",
        badge_id: 9,
      },{
        titel:"Veiligheidspunten bij het gebruik van materiaal kennen",
        badge_id: 9,
      },{
        titel:"Platte knoop",
        badge_id: 10,
      },{
        titel:"Achtsteek",
        badge_id: 10,
      },{
        titel:"Mastworp",
        badge_id: 10,
      },{
        titel:"Timmermanssteek",
        badge_id: 10,
      },{
        titel:"Schootsteek",
        badge_id: 10,
      },{
        titel:"Trompetsteek",
        badge_id: 10,
      },{
        titel:"Een kruissjorring kunnen maken",
        badge_id: 10,
      },{
        titel:"Een driepikkel kunnen maken",
        badge_id: 10,
      },{
        titel:"Een touw van 10 meter kunnen oprollen",
        badge_id: 10,
      },{
        titel:"Weten hoe een sjorbalk te behandelen",
        badge_id: 10,
      },{
        titel:"Weten hoe en wanneer de verkortingsknoop te gebruiken",
        badge_id: 11,
      },{
        titel:"Piketten en haringen correct kunnen inslaan",
        badge_id: 11,
      },{
        titel:"Weten hoe een tent voor te bereiden op onweer",
        badge_id: 11,
      },{
        titel:"Patrouilletent correct opzetten, afbreken en opbergen",
        badge_id: 11,
      },{
        titel:"Maxbitent correct opzetten, afbreken en opbergen",
        badge_id: 11,
      },{
        titel:"Onderscheid kennen tussen de verschillende grondzeilen",
        badge_id: 11,
      },{
        titel:"Rugzak behoorlijk kunnen inpakken voor een dagtocht",
        badge_id: 12,
      },{
        titel:"Weten wat te doen als het regent",
        badge_id: 12,
      },{
        titel:"Draagriemen van je rugzak kunnen aanpassen",
        badge_id: 12,
      },{
        titel:"Persoonlijke maten kennen en kunnen gebruiken",
        badge_id: 13,
      },{
        titel:"Persoonlijke pas gebruiken om een afstand te schatten",
        badge_id: 13,
      },
      
    ])
  }
}