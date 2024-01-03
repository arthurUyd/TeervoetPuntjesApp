const {
  tables,
  getKnex
} = require('../data/index');
const {
  getLogger
} = require('../core/logging');
const SELECT_COLUMNS = [
  `${tables.puntjes}.id `,`${tables.puntjes}.titel`, `${tables.badge}.id as badge_id`,
];

const findAll = async () => {
  const puntjes = await getKnex()(tables.puntjes)
    .select(SELECT_COLUMNS) 
    .from(tables.puntjes)
    .join(tables.badge, `${tables.puntjes}.badge_id`,'=', `${tables.badge}.id`);
  return puntjes;
}

module.exports = {  
  findAll
};
