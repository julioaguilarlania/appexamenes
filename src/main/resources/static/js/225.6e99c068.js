"use strict";(globalThis["webpackChunkappexamenesf"]=globalThis["webpackChunkappexamenesf"]||[]).push([[225],{2225:(e,o,n)=>{n.r(o),n.d(o,{default:()=>g});var a=n(9835);const l={class:"q-pa-md"};function s(e,o,n,s,t,i){const c=(0,a.up)("q-btn"),m=(0,a.up)("q-td"),r=(0,a.up)("q-table");return(0,a.wg)(),(0,a.iD)("div",l,[(0,a.Wm)(r,{title:"Alumnos",rows:e.alumnos,columns:e.columnasAlumnos,"row-key":"idAlumno",dense:"",pagination:e.paginacion,"rows-per-page-options":[0],"hide-bottom":""},{"body-cell-acciones":(0,a.w5)((e=>[(0,a.Wm)(m,{props:e},{default:(0,a.w5)((()=>[(0,a.Wm)(c,{dense:"",round:"",flat:"",color:"grey",to:{path:"/misexamenes/"+e.row.idAlumno},icon:"pageview",title:"Navegar como"},null,8,["to"])])),_:2},1032,["props"])])),_:1},8,["rows","columns","pagination"])])}const t=(0,a.aZ)({name:"ExamenesPage",data:function(){return{idExpEd:this.$route.params.idee,alumnos:[],columnasAlumnos:[{name:"nombres",label:"Nombre(s)",field:"nombres",align:"left"},{name:"apellidos",label:"Apellidos",field:"apellidos",align:"left"},{name:"acciones",label:"Ver como",field:"",align:"center"}],paginacion:{page:1,rowsPerPage:0}}},mounted:function(){this.cargarAlumnos()},methods:{cargarAlumnos:function(){console.log("cargarAlumnos()"),this.$api.get("alumno/?idee=1").then((e=>{console.log("GET ejecutado"),this.alumnos=e.data})).catch((e=>{console.log("ERROR"),console.log(e)}))}}});var i=n(1639),c=n(1971),m=n(7220),r=n(4455),u=n(9984),p=n.n(u);const d=(0,i.Z)(t,[["render",s]]),g=d;p()(t,"components",{QTable:c.Z,QTd:m.Z,QBtn:r.Z})}}]);