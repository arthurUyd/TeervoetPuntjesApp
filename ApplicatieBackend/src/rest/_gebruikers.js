const Joi = require('joi');
const Router = require('@koa/router');
const validation = require('./_validation')
const gebruikersService = require('../service/gebruikers');

const getAllGebruikers = async (ctx) => {
  ctx.body = await gebruikersService.getAll();
}
getAllGebruikers.validationScheme = null;

const getGebruikerByEmail = async (ctx) => {
  const gebruiker = await gebruikersService.getByEmail(ctx.params.email, ctx.request.body.password);
  ctx.body = {
    ...gebruiker
  };
  ctx.status = 201;
}
getGebruikerByEmail.validationScheme = {
  params: {
    email: Joi.string().required()
  }
};

const createGebruiker = async (ctx) => {
  console.log(ctx.request.body);
  const newGebruiker = await gebruikersService.create(ctx.request.body);
  ctx.body = newGebruiker;
  ctx.status = 201;
}
createGebruiker.validationScheme = {
  body: {
    naam: Joi.string().required(),
    email: Joi.string().required(),
    password: Joi.string().required(),
  }
};

const getPuntjesById = async (ctx) => {
  const puntjes = await gebruikersService.getPuntjesById(ctx.params.id);
  ctx.body = puntjes;
  ctx.status = 201;
}
getPuntjesById.validationScheme = {
  params: {
    id: Joi.number().required(),
  },
}

const addPuntjes = async (ctx) => {
  console.log(ctx.request.body);
  const gebruikerId = ctx.params.id;
  const puntjesIds = ctx.request.body;
  
  const puntjes = await gebruikersService.addPuntjes(gebruikerId, puntjesIds);
  ctx.body = puntjes;
  ctx.status = 201;
}
addPuntjes.validationScheme = {
  params: {
    id: Joi.number().required(),
  },
  
}


module.exports = (app) => {
  const router = new Router({
    prefix: '/gebruikers'
  });

  router.get('/', validation(getAllGebruikers.validationScheme), getAllGebruikers);
  router.get('/:email', validation(getGebruikerByEmail.validationScheme), getGebruikerByEmail);
  router.post('/', validation(createGebruiker.validationScheme), createGebruiker);
  router.post('/puntjes/:id', validation(addPuntjes.validationScheme), addPuntjes);
  router.get('/puntjes/:id', validation(getPuntjesById.validationScheme), getPuntjesById);
  app.use(router.routes()).use(router.allowedMethods());
}