var levelsSectionHeader = "<hr>" +
            "<h3>List of levels</h3>";
            
var levelsTableStructure = '<table class="table">' + 
            ' <thead class="thead-dark">' + 
            '   <tr>' +
            '     <th scope="col"></th>' +
            '     <th scope="col">Name</th>' +
            '     <th scope="col" class="center-column">Experience threshold</th>' +
            '     <th scope="col"></th>' +
            '   </tr>' +
            ' </thead>' +
            ' <tbody>' +
            ' </tbody>' +
            '</table>';


async function createTableFromJson(jsonPath) {
    const levelsTableDiv = document.getElementById("levels-table");
    const levelsJson = await getJsonFromPath(jsonPath);

    if (levelsJson.levels.length === 0) {
      return null;
    } 

    levelsTableDiv.innerHTML = levelsSectionHeader + levelsTableStructure;
    addLevelsRows(levelsJson, levelsTableDiv);
}

async function getJsonFromPath(path) {
    const response = await fetch(path);
    return response.json();
}

function addLevelsRows(levelsJson, levelsDiv) {
  const levelsTableBody = levelsDiv.querySelector("tbody");

  for (let i = 0; i < levelsJson.levels.length; i++) {
    const level = levelsJson.levels[i];

    const filledRow = '<tr>' +
      '  <th scope="row">' + (i+1) + '</th>' +
      '  <td>' + level.name + '</td>' +
      '  <td class="center-column">' + level.threshold + '</td>' +
      '  <td class="center-column">' +
      '    <i class="fas fa-pencil-alt" id="open-edit-level-modal" data-id=' + level.id + ' onclick="openModal(this.id, this)"></i>' +
      '    <i class="fas fa-trash-alt" id="open-remove-level-modal" data-id=' + level.id + ' onclick="openModal(this.id, this)"></i>' +
      '  </td>' +
      '</tr>';

      levelsTableBody.innerHTML += filledRow;
  }
}

createTableFromJson("http://127.0.0.1:8080/levels");