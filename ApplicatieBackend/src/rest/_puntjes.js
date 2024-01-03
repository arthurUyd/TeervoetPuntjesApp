const Router = require('@koa/router');
const puntjesService = require('../service/puntjes');

const getAllPuntjes = async (ctx) => {
  ctx.body = await puntjesService.getAll();
}

module.exports = (app) => {
  const router = new Router({
    prefix: '/puntjes'
  });

  router.get('/', getAllPuntjes);
  app.use(router.routes());
}