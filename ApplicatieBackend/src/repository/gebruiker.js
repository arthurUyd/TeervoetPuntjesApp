const {
  tables,
  getKnex
} = require('../data/index');
const {
  getLogger
} = require('../core/logging');
const SELECT_COLUMNS = [
  `${tables.gebruiker}.id `, 'naam', 'email', 'password', 'isLeider'
];

const findAll = async () => {
  const gebruikers = await getKnex()(tables.gebruiker)
    .select(SELECT_COLUMNS)
    .from(tables.gebruiker);
  return gebruikers;
}

const findByEmail = async (email,password) => {
  const gebruiker = await getKnex()(tables.gebruiker)
    .select(SELECT_COLUMNS)
    .from(tables.gebruiker)
    .where('email', email)
    .andWhere('password', password)
    .first();
  return gebruiker;
}

const findPuntjesByGebruikerId = async (gebruikerId) => {
  const puntjes = await getKnex()(tables.gebruiker_puntjes)
    .select('punt_id')
    .from(tables.gebruiker_puntjes)
    .where('user_id', gebruikerId)
  return puntjes;
}

const create = async (
  naam,
  email,
  password,

) => {
  const trx = await getKnex().transaction();

  try {
    const [id] = await trx(tables.gebruiker)
      .insert({
        naam,
        email,
        password,
      });
    await trx.commit();
    return id;
  } catch (error) {
    await trx.rollback();
    const logger = getLogger();
    logger.error(`Fout bij het aanmaken van een gebruiker.`, {
      error
    });
    throw error;
  }
}

const addPuntjes = async (
  gebruikerId,
  puntIds,
) => {
  const trx = await getKnex().transaction();
  try {
    const inserts = puntIds.map(async (puntId) => {
      const userid = gebruikerId;
      const puntid = puntId;

      return trx(tables.gebruiker_puntjes)
        .insert({
          user_id : userid,
          punt_id: puntid,
        });
    });
    await Promise.all(inserts);
    await trx.commit();
  } catch (error) {
    await trx.rollback();
    const logger = getLogger();
    logger.error(`Fout bij het toevoegen van puntjes aan gebruiker.`, {
      error
    });
    throw error;
  }
}


module.exports = {
  findAll,
  findByEmail,
  create,
  findPuntjesByGebruikerId,
  addPuntjes
};