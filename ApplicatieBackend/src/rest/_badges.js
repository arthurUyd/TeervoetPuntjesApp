const Router = require('@koa/router');
const validation = require('./_validation')
const badgeService = require('../service/badges');

const getAllBadges = async (ctx) => {
 const badges = await badgeService.getAll();
 ctx.body = badges;
}
getAllBadges.validationScheme = null;

module.exports = (app) => {
  const router = new Router({
    prefix: '/badges'
  });

  router.get('/',validation(getAllBadges.validationScheme), getAllBadges);
  app.use(router.routes());
}