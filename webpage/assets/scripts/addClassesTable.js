var classesSectionHeader = '<hr>' +
            '<h3>List of classes</h3>';
            
var classesTableStructure = '<table class="table">' + 
						'	<thead class="thead-dark">' + 
						'		<tr>' + 
						'			<th scope="col"></th>' + 
						'			<th scope="col">Name</th>' + 
						'			<th scope="col"></th>' + 
						'		</tr>' + 
            '	</thead>' +
            ' <tbody>' +
            ' </tbody>' +
            '</table>';


async function createTableFromJson(jsonPath) {
    const classesTableDiv = document.getElementById("classes-table");
    const classesJson = await getJsonFromPath(jsonPath);

    if (classesJson.classes.length === 0) {
      return null;
    } 

    classesTableDiv.innerHTML = classesSectionHeader + classesTableStructure;
    addClassRows(classesJson, classesTableDiv);
}

async function getJsonFromPath(path) {
    const response = await fetch(path);
    return response.json();
}

function addClassRows(classesJson, classesDiv) {
  const classesTableBody = classesDiv.querySelector("tbody");

  for (let i = 0; i < classesJson.classes.length; i++) {
    const codecoolClass = classesJson.classes[i];
    const filledRow = '<tr>' +
      '  <th scope="row">' + (i + 1) + '</th>' +
      '  <td>' + codecoolClass.name + '</td>' +
      '  <td class="center-column">' +
      '    <i class="fas fa-pencil-alt" id="open-edit-class-modal" data-id=' + codecoolClass.id + ' onclick="openModal(this.id, this)"></i>' +
      '    <i class="fas fa-trash-alt" id="open-remove-class-modal" data-id=' + codecoolClass.id + ' onclick="openModal(this.id, this)"></i>' +
      '  </td>' +
      '</tr>';

      classesTableBody.innerHTML += filledRow;
  }
}

createTableFromJson("http://127.0.0.1:8080/classes");