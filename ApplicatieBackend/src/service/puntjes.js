const {getLogger} = require('../core/logging');
const puntRepo = require('../repository/puntje');

const debugLog = (message, meta = {}) => {
  if (!this.logger) this.logger = getLogger();
  this.logger.debug(message, meta);
}

const getAll = async () => {
  debugLog('Getting all puntjes');
  return await puntRepo.findAll();
}

module.exports = {
  getAll
}