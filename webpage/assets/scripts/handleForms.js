function handleEditMentorForm(form) {
  var login = form.dataset.id;

  var inputs = form.getElementsByTagName("input");

  var firstName = inputs[0].value;
  var lastName = inputs[1].value;
  var email = inputs[2].value;

  mentorData = {
    "login": elementId,
    "first_name": firstName,
    "last_name": lastName,
    "email": email
  }

  console.log(JSON.stringify(mentorData));

  var url = 'http://127.0.0.1:8080/mentors/' + login;

  fetch(url, {
    method: 'PUT',
    body: JSON.stringify(levelData),
    headers:{
      'Content-Type': 'application/json'
    }
  });

  $('#common-modal').modal('hide');

  return false;
}

function deleteMentor(deleteButton) {
  var login = deleteButton.dataset.id;

  var url = 'http://127.0.0.1:8080/mentors/' + login;

  fetch(url, {
    method: 'DELETE',
  });

  $('#common-modal').modal('hide');

  return false;
}

function handleEditClassForm(form) {
  var elementId = form.dataset.id;

  var inputs = form.getElementsByTagName("input");

  var name = inputs[0].value;

  classData = {
    "id": elementId,
    "name": name
  }

  console.log(JSON.stringify(classData));

  var url = 'http://127.0.0.1:8080/classes/' + elementId;

  fetch(url, {
    method: 'PUT',
    body: JSON.stringify(classData),
    headers:{
      'Content-Type': 'application/json'
    }
  });

  $('#common-modal').modal('hide');

  return false;
}

function deleteClass(deleteButton) {
  var elementId = deleteButton.dataset.id;

  var url = 'http://127.0.0.1:8080/classes/' + elementId;

  fetch(url, {
    method: 'DELETE',
  });

  $('#common-modal').modal('hide');

  return false;
}

function handleEditLevelForm(form) {
  var elementId = form.dataset.id;

  var inputs = form.getElementsByTagName("input");

  var name = inputs[0].value;
  var threshold = inputs[1].value;

  levelData = {
    "id": elementId,
    "name": name,
    "threshold": threshold
  }

  console.log(JSON.stringify(levelData));

  var url = 'http://127.0.0.1:8080/levels/' + elementId;

  fetch(url, {
    method: 'PUT',
    body: JSON.stringify(levelData),
    headers:{
      'Content-Type': 'application/json'
    }
  });

  $('#common-modal').modal('hide');

  return false;
}

function deleteLevel(deleteButton) {
  var elementId = deleteButton.dataset.id;

  var url = 'http://127.0.0.1:8080/levels/' + elementId;

  fetch(url, {
    method: 'DELETE',
  });

  $('#common-modal').modal('hide');

  return false;
}

function buyArtifact(buyButton) {
  var artifactId = buyButton.dataset.id;

  var url = 'http://127.0.0.1:8080/store/' + artifactId;

  fetch(url, {
    method: 'POST',
  });

  $('#common-modal').modal('hide');

  return false;
}