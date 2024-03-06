const {getLogger} = require('../core/logging');
const ServiceError = require('../core/serviceError');
const gebruikerRepo = require('../repository/gebruiker');

const debugLog = (message, meta = {}) => {
  if (!this.logger) this.logger = getLogger();
  this.logger.debug(message, meta);
}

const getAll = async () => {
  debugLog('Getting all gebruikers');
  return await gebruikerRepo.findAll();
}

const getByEmail = async (email, password) => {
  debugLog(`Getting gebruiker with email: ${email}`);
  const gebruiker = await gebruikerRepo.findByEmail(email,password);
  if (!gebruiker) {
    throw ServiceError(`Gebruiker with id ${id} not found`);
  }
  return gebruiker;
}

const getPuntjesById = async (id) => {
  const puntjes = await gebruikerRepo.findPuntjesByGebruikerId(id);
  return puntjes;
}

const addPuntjes = async(
  gebruikerId,
  puntjesId,
) => {
  debugLog(`Adding puntjes to gebruiker with id: ${gebruikerId}`);
  const puntjes = await gebruikerRepo.addPuntjes(gebruikerId, puntjesId);
  return puntjes;
}



const create = async(
  {naam,
  email,
  password,}
) => {
  debugLog(`Creating gebruiker with email: ${email}`);
  if(!email||!naam||!password) {
    throw ServiceError.validationFailed('missing required fields'); 
  } 
  const id = await gebruikerRepo.create(
    naam,
    email,
    password,
  );
  if(!id) {
    throw ServiceError.notFound(`Gebruiker with email ${email} not created`);
  }
  return id;
}

module.exports = {
  getAll,
  getByEmail,
  create,
  getPuntjesById,
  addPuntjes
}


