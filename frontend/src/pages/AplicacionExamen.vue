<template>
  <q-page>
    <div class="q-pa-md row">
      <q-space />
    <q-btn icon="add" color="primary" @click="guardarReactivos()"> Enviar</q-btn>
    </div>
    <div class="q-pa-md" v-for="(r, indice) in reactivos">
    <q-card >
      <q-card-section>
        <b>{{indice+1}}. {{r.texto}}</b>
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <div><q-radio v-model="resp[indice]" val="1"/>{{r.opcion1}}</div>
        <div><q-radio v-model="resp[indice]" val="2"/>{{r.opcion2}}</div>
        <div><q-radio v-model="resp[indice]" val="3"/>{{r.opcion3}}</div>
        <div v-if="r.opcion4"><q-radio v-model="resp[indice]" val="4"/>{{r.opcion4}}</div>
      </q-card-section>
    </q-card>
    </div>
      <div>
<div q-pa-md q-gutter-sm>
<q-dialog v-model="confirm" persistent>
      <q-card>
        <q-card-section class="row items-center">
          <q-avatar icon="delete" color="primary" text-color="white" />
          <span class="q-ml-sm">¿Estás seguro de guardar el examen?</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="Enviar" color="primary" @click="enviarConfirmado()" />
          <q-btn flat label="Cancelar" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
</div>

      </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'AplicacionExamenPage',
  data: function() {
    return {
      idExamen: this.$route.params.idex,
      idAlumno: this.$route.params.idal,
      editando: false,
      reactivos: [],
      resp: [],
      expSeleccionada: {id:1,label:"Desarrollo de Software"},
      exps: [{id:1,label:"Desarrollo de Software"}],
      confirm: false,
      columnasReactivos: [
        {
          name: "texto",
          label: "Pregunta",
          field: "texto",
          align: "left"
        },
        {
          name:"acciones",
          label:"Acciones",
          field:"",
          align:"center"
        }
      ]
    };
  },
mounted: function() {
    this.cargarReactivos();
    this.crearExamenAlumno();
},
methods: {
    cargarReactivos: function() {
        console.log("cargarReactivos()");
        this.$api.get("reactivo?idExamen=" + this.idExamen)
            .then(respuesta => {
                console.log("GET ejecutado");
                this.reactivos = respuesta.data;
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
    },
    crearExamenAlumno: function() {
      console.log("crearExamenAlumno");
      let examenAlumno = {"idExamen":this.idExamen, "idAlumno":this.idAlumno};
      this.$api.post("examen-alumnos", examenAlumno)
        .then(respuesta => { console.log("examen abierto")})
        .catch(err => {console.log(err)});

    },
    guardarReactivos: function() {
        console.log("guardarReactivos()");
        let respuestasReactivos = {
          "idExamen": this.idExamen,
          "idAlumno": this.idAlumno,
          "respuestas": []
        }
        for (let mm = 0; mm < this.reactivos.length; mm++) {
        console.log(this.reactivos[mm],this.resp[mm]);
          respuestasReactivos.respuestas.push({
            "idReactivo": this.reactivos[mm].idReactivo,
            "opcion": this.resp[mm]
          });
        }
        this.$api.post("/respuesta-reactivos", respuestasReactivos)
          .then(respuesta => {
            console.log("examen cerrado");
            this.$router.push("/misexamenes/" + this.idAlumno);
          })
          .catch(err => {console.log(err)});
    }
}

})
</script>
