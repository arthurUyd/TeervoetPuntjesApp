const {getLogger} = require('../core/logging');
const badgeRepo = require('../repository/badge');

const debugLog = (message, meta = {}) => {
  if (!this.logger) this.logger = getLogger();
  this.logger.debug(message, meta);
}

const getAll = async () => {
  debugLog('Getting all badges');
  return await badgeRepo.findAll();
}

module.exports = {
  getAll
}
