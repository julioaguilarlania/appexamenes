
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/IndexPage.vue') },
      { path: '/examenes', component: () => import('pages/Examenes.vue')},
      { path: '/reactivos', component: () => import('pages/Reactivos.vue')},
      { path: '/seleccion_reactivos/:idex', component: () => import('pages/SeleccionReactivos.vue')},
      { path: '/misexamenes/:idal', component: () => import('pages/ExamenesPorAlumno.vue')},
      { path: '/aplicar_examen/:idex/:idal', component: () => import('pages/AplicacionExamen.vue')},
      { path: '/calificaciones_examen/:idex', component: () => import('pages/CalificacionesExamen.vue')},
      { path: '/alumnos/:idee', component: () => import('pages/ListaAlumnos.vue')},
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
