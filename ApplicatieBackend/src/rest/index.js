const Router = require('@koa/router');

const installBadgeRouter = require('./_badges');
const installGebruikerRouter = require('./_gebruikers');
const installPuntjesRouter = require('./_puntjes');

module.exports = (app) => {
  const router = new Router({prefix: '/api'});
  
  installBadgeRouter(router);
  installGebruikerRouter(router);
  installPuntjesRouter(router);

  
  app.use(router.routes());
  app.use(router.allowedMethods());
}