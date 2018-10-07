function openAddModal(buttonId) {
  var modalFilePath;

  switch (buttonId) {
    case "open-add-mentor-modal":
      modalFilePath = "assets/modals/add_mentor_modal.html";
      break;
    case "open-add-class-modal":
      modalFilePath = "assets/modals/add_class_modal.html";
      break;
    case "open-add-level-modal":
      modalFilePath = "assets/modals/add_level_modal.html";
      break;
  }
  
  var element = document.getElementById("common-modal").querySelector(".modal-content");

  var request = new XMLHttpRequest();
  request.onreadystatechange = function() {
    if (this.readyState == 4) {
      if (this.status == 200) {
        element.innerHTML = this.responseText;
        $('#common-modal').modal('show');
      }
      if (this.status == 404) {
        element.innerHTML = "Page not found.";
      }
    }
  } 
  request.open("GET", modalFilePath, true);
  request.send();
  
  return;
}

async function openModal(buttonId, button) {
  var modalFilePath;
  var fillingFunction;

  var element = document.getElementById("common-modal").querySelector(".modal-content");

  var entityId = button.dataset.id;

  switch (buttonId) {
    case "open-edit-mentor-modal":
      element.innerHTML = await addFilledEditMentorModal(entityId);
      break;
    case "open-remove-mentor-modal":
      element.innerHTML = await addRemoveMentorModal(entityId);
      break;
    case "open-edit-class-modal":
      element.innerHTML = await addFilledEditClassModal(entityId);
      break;
    case "open-remove-class-modal":
      element.innerHTML = await addRemoveClassModal(entityId);
      break;
    case "open-edit-level-modal":
      element.innerHTML = await addFilledEditLevelModal(entityId);
      break;
    case "open-remove-level-modal":
      element.innerHTML = await addRemoveLevelModal(entityId);
      break;
    case "open-buy-artifact-modal":
      element.innerHTML = await addBuyArtifactModal(entityId);
      break
  }
  
  $('#common-modal').modal('show');
  
  return;
}

async function addFilledEditMentorModal(login) {
  var mentorData = await getJsonFromPath("http://127.0.0.1:8080/mentors/" + login);
  var mentorData = mentorData.mentors[0];

  var filledModal = 
  '<div class="modal-header">' +
  '  <h5 class="modal-title">Edit mentor</h5>' +
  '  <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
  '    <span aria-hidden="true">&times;</span>' +
  '  </button>' +
  '</div>' +
  '<div class="modal-body">' +
  '  <form id="editMentorForm" data-id="' + mentorData.login + '" onsubmit="return handleEditMentorForm(this)">' +
  '    <div class="form-group">' +
  '      <label for="mentor-first-name">First name</label>' +
  '      <input type="text" class="form-control" id="mentor-first-name" value="' + mentorData.first_name + '">' +
  '    </div>' +
  '    <div class="form-group">' +
  '      <label for="mentor-last-name">Last name</label>' +
  '      <input type="text" class="form-control" id="mentor-last-name" value="' + mentorData.last_name + '">' +
  '    </div>' +
  '    <div class="form-group">' +
  '      <label for="mentor-email">Email address</label>' +
  '      <input type="email" class="form-control" id="mentor-email" value="' + mentorData.email + '">' +
  '    </div>' +
  '    <div class="form-group">' +
  '      <label>Select classes</label>' +

  '      <div class="custom-control custom-checkbox">' +
  '        <input type="checkbox" class="custom-control-input" id="customCheck1" checked>' +
  '        <label class="custom-control-label" for="customCheck1">2017.11</label>' +
  '      </div>' +
  '      <div class="custom-control custom-checkbox">' +
  '        <input type="checkbox" class="custom-control-input" id="customCheck2">' +
  '        <label class="custom-control-label" for="customCheck2">2018.04</label>' +
  '      </div>' +
  '      <div class="custom-control custom-checkbox">' +
  '        <input type="checkbox" class="custom-control-input" id="customCheck3" checked>' +
  '        <label class="custom-control-label" for="customCheck3">2018.05</label>' +
  '      </div>' +
  '    </div>' +
  '    <div class="modal-footer">' +
  '      <button type="submit" class="btn btn-success">Save changes</button>' +
  '      <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>' +
  '    </div>' +
  '  </form>' +
  '</div>'; 

  return filledModal;
}

async function addRemoveMentorModal(login) {
  var mentorData = await getJsonFromPath("http://127.0.0.1:8080/mentors/" + login);
  var mentorData = mentorData.mentors[0];

  var filledModal =
  '<div class="modal-header">' +
  '  <h5 class="modal-title">Remove mentor</h5>' +
  '  <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
  '    <span aria-hidden="true">&times;</span>' +
  '  </button>' +
  '</div>' +
  '<div class="modal-body">' +
  '  <p>Are you sure you want to delete ' + mentorData.first_name + ' ' + mentorData.last_name + ' </p>' +
  '</div>' +
  '<div class="modal-footer">' +
  '  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
  '  <button type="submit" class="btn btn-danger" data-id="' + mentorData.login + '" onclick="deleteMentor(this); javascript:window.location.reload()">Delete</button>' +
  '</div>'; 

  return filledModal;
}

async function addFilledEditClassModal(id) {
  var classData = await getJsonFromPath("http://127.0.0.1:8080/classes/" + id);
  var classData = classData.classes[0];

  var filledModal = 
  '<div class="modal-header">' +
  '  <h5 class="modal-title">Edit class</h5>' +
  '  <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
  '    <span aria-hidden="true">&times;</span>' +
  '  </button>' +
  '</div>' +
  '<div class="modal-body">' +
  '  <form id="editClassForm" data-id="' + classData.id + '" onsubmit="return handleEditClassForm(this)">' +
  '  <form>' +
  '    <div class="form-group">' +
  '      <label for="codecool-class-name">Class name</label>' +
  '      <input type="text" class="form-control" id="codecool-class-name" value="' + classData.name + '" placeholder="Enter class name">' +
  '    </div>' +
  '    <div class="modal-footer">' +
  '      <button type="submit" class="btn btn-success">Save changes</button>' +
  '      <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>' +
  '    </div>' +
  '  </form>' +
  '</div>';

  return filledModal;
}

async function addRemoveClassModal(id) {
  var classData = await getJsonFromPath("http://127.0.0.1:8080/classes/" + id);
  var classData = classData.classes[0];

  var filledModal =
  '<div class="modal-header">' +
  '  <h5 class="modal-title">Remove class</h5>' +
  '  <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
  '    <span aria-hidden="true">&times;</span>' +
  '  </button>' +
  '</div>' +
  '<div class="modal-body">' +
  '  <p>Are you sure you want to delete ' + classData.name + ' </p>' +
  '</div>' +
  '<div class="modal-footer">' +
  '  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
  '  <button type="submit" class="btn btn-danger" data-id="' + classData.id + '" onclick="deleteClass(this); javascript:window.location.reload()">Delete</button>' +
  '</div>';

  return filledModal;
}

async function addFilledEditLevelModal(id) {
  var levelData = await getJsonFromPath("http://127.0.0.1:8080/levels/" + id);
  var levelData = levelData.levels[0];

  var filledModal = 
  '<div class="modal-header">' +
  '  <h5 class="modal-title">Edit level</h5>' +
  '  <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
  '    <span aria-hidden="true">&times;</span>' +
  '  </button>' +
  '</div>' +
  '<div class="modal-body">' +
  '   <form id="editLevelForm" data-id="' + levelData.id + '" onsubmit="return handleEditLevelForm(this)">' +
  '    <div class="form-group">' +
  '      <label for="codecool-class-name">Level name</label>' +
  '      <input type="text" class="form-control" id="codecool-class-name" name="name" value="' 
            + levelData.name + '" placeholder="Enter level name">' +
  '      <small class="form-text text-muted">Required field</small>' +
  '    </div>' +
  '    <div class="form-group">' +
  '      <label for="codecool-class-name">Experience threshold</label>' +
  '      <input type="number" class="form-control" id="codecool-class-threshold" name="threshold" value="' 
            + levelData.threshold + '" placeholder="Enter experience threshold">' +
  '      <small class="form-text text-muted">Required field</small>' +
  '    </div>' +
  '      <div class="modal-footer">' +
  '        <button type="submit" class="btn btn-success">Save changes</button>' +
  '        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>' +
  '      </div>' +
  '  </form>' +
  '</div>';

  return filledModal;
}

async function addRemoveLevelModal(id) {
  var levelData = await getJsonFromPath("http://127.0.0.1:8080/levels/" + id);
  var levelData = levelData.levels[0];

  var filledModal =
  '<div class="modal-header">' +
  '  <h5 class="modal-title">Remove level</h5>' +
  '  <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
  '    <span aria-hidden="true">&times;</span>' +
  '  </button>' +
  '</div>' +
  '<div class="modal-body">' +
  '  <p>Are you sure you want to delete ' + levelData.name + '</p>' +
  '</div>' +
  '<div class="modal-footer">' +
  '  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
  '  <button type="submit" class="btn btn-danger" data-id="' + levelData.id + '" onclick="deleteLevel(this); javascript:window.location.reload()">Delete</button>' +
  '</div>'; 

  return filledModal;
}

async function addBuyArtifactModal(id) {
  var artifactData = await getJsonFromPath("http://127.0.0.1:8080/store/" + id);
  var artifactData = artifactData.artifacts[0];

  var filledModal =
  '<div class="modal-header">' +
  '  <h5 class="modal-title">Buy artifact</h5>' +
  '  <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
  '    <span aria-hidden="true">&times;</span>' +
  '  </button>' +
  '</div>' +
  '<div class="modal-body">' +
  '  <p>Do you really want to buy ' + artifactData.name + '?</p>' +
  '</div>' +
  '<div class="modal-footer">' +
  '  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
  '  <button type="submit" class="btn btn-success" data-id="' + artifactData.id + '" onclick="buyArtifact(this); javascript:window.location.reload()">Buy</button>' +
  '</div>';

  return filledModal;
}