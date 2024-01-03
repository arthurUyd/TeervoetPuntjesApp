const {
  tables,
  getKnex
} = require('../data/index');
const {
  getLogger
} = require('../core/logging');
const SELECT_COLUMNS = [
  `${tables.badge}.id `, 'titel', 'image_url'
];

const findAll = async () => {
  const badges = await getKnex()(tables.badge)
    .select(SELECT_COLUMNS) 
    .from(tables.badge);
  return badges;
}

module.exports = {  
  findAll
};

